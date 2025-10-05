package com.materiais.araujo.araujo_materiais_api.service;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CadastroDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.CpfJaCadastradoExeception;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.EmailJaCadastradoException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.CodigoAutorizacao;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.StatusUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.CodigoAutorizacaoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    public AutenticacaoService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                               CodigoAutorizacaoRepository codigoAutorizacaoRepository,
                               AuthenticationManager authenticationManager, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.codigoAutorizacaoRepository = codigoAutorizacaoRepository;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    public ResponseEntity<String> cadastrarUsuario(CadastroDTO dto) {
        if (this.usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailJaCadastradoException();
        }
        if (this.usuarioRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new CpfJaCadastradoExeception();
        }

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Usuario usuario = new Usuario(dto.nome(), dto.cpf(), dto.email(),
                senhaCriptografada, RoleUsuario.CLIENTE, StatusUsuario.INATIVO);

        String codigo = UUID.randomUUID().toString().substring(0, 10);
        Instant horaioEnvio = Instant.now();
        Instant horarioDeExpiracao = horaioEnvio.plusMillis(900000);

        CodigoAutorizacao codigoAutorizacao = new CodigoAutorizacao(usuario, codigo, horaioEnvio, horarioDeExpiracao);


        usuarioRepository.save(usuario);
        codigoAutorizacaoRepository.save(codigoAutorizacao);
        emailService.enviarEmail(usuario.getEmail(), "Codigo de validacao",
                "O seu codigo de validacao é: " + codigoAutorizacao.getCodigo());

        return ResponseEntity.ok().body("Cadastro realizado com sucesso, agora verifique o seu email!");
    }
}
