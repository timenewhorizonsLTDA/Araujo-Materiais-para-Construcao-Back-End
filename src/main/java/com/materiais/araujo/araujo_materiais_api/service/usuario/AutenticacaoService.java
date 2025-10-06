package com.materiais.araujo.araujo_materiais_api.service.usuario;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CadastroDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CodigoValidacaoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.LoginDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.TokenDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.*;
import com.materiais.araujo.araujo_materiais_api.infra.security.TokenService;
import com.materiais.araujo.araujo_materiais_api.model.usuario.CodigoAutorizacao;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.StatusUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.CodigoAutorizacaoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AutenticacaoService {

    private UsuarioRepository usuarioRepository;

    private CodigoAutorizacaoRepository codigoAutorizacaoRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private EmailService emailService;

    private UtilUsuario utilUsuario;

    private TokenService tokenService;


    public AutenticacaoService(UsuarioRepository usuarioRepository,
                               CodigoAutorizacaoRepository codigoAutorizacaoRepository,
                               PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                               EmailService emailService, UtilUsuario utilUsuario, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.codigoAutorizacaoRepository = codigoAutorizacaoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.utilUsuario = utilUsuario;
        this.tokenService = tokenService;
    }

    public ResponseEntity<String> cadastrarUsuario(CadastroDTO dto) {
        if (this.usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailJaCadastradoException();
        }
        if (this.usuarioRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new CpfJaCadastradoExeception();
        }

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Usuario usuario = new Usuario(dto.nome(), dto.cpf(), dto.email(), senhaCriptografada, RoleUsuario.CLIENTE, StatusUsuario.INATIVO);

        String codigo = UUID.randomUUID().toString().substring(0, 10);
        Instant horaioEnvio = Instant.now();
        Instant horarioDeExpiracao = horaioEnvio.plusMillis(600000);

        CodigoAutorizacao codigoAutorizacao = new CodigoAutorizacao(usuario, codigo, horaioEnvio, horarioDeExpiracao);


        usuarioRepository.save(usuario);
        codigoAutorizacaoRepository.save(codigoAutorizacao);
        emailService.enviarEmail(usuario.getEmail(), "Codigo de validacao", "O seu codigo de validacao é: " + codigoAutorizacao.getCodigo());

        return ResponseEntity.ok().body("Cadastro realizado com sucesso, agora verifique o seu email!");
    }

    public ResponseEntity<String> validarUsuario(CodigoValidacaoDTO dto) {

        CodigoAutorizacao codigoAutorizacao = codigoAutorizacaoRepository.findByCodigo(dto.codigo())
                .orElseThrow(() -> new CodigoDeValidacaoNaoValidoException());

        Usuario usuario = codigoAutorizacao.getUsuario();

        Instant horaAtual = Instant.now();

        if (horaAtual.isAfter(codigoAutorizacao.getHorarioDeExpiracao())) {

            this.codigoAutorizacaoRepository.delete(codigoAutorizacao);

            if (usuario.getStatusUsuario() == StatusUsuario.INATIVO) {
                this.usuarioRepository.delete(usuario);
            }

            throw new CodigoDeValidacaoExpiradoException();
        }

        usuario.setStatusUsuario(StatusUsuario.ATIVO);
        this.usuarioRepository.save(usuario);
        this.codigoAutorizacaoRepository.delete(codigoAutorizacao);

        return ResponseEntity.ok().body("Validacao concluida com sucesso, agora realize o login");
    }

    public ResponseEntity<TokenDTO> login(LoginDTO dto){

        Usuario usuario = utilUsuario.obterUsuarioEmail(dto.email());

        if(usuario.getStatusUsuario() == StatusUsuario.INATIVO){
            throw new UsuarioInativoException();
        }

        var usernameToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

        authenticationManager.authenticate(usernameToken);

        String token = tokenService.gerarToken(usuario);

        TokenDTO tokenDTO = new TokenDTO(token);

        return ResponseEntity.ok().body(tokenDTO);

    }



}
