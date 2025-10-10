package com.materiais.araujo.araujo_materiais_api.service.gerente;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioResponseDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.FuncionarioJaExistenteException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.StatusUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.usuario.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GerenteService {

    private UsuarioRepository usuarioRepository;

    private EmailService emailService;

    private PasswordEncoder passwordEncoder;

    public GerenteService(UsuarioRepository usuarioRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<CadastrarFuncionarioResponseDTO> cadastrarFuncionario(CadastrarFuncionarioDTO dto) {

        if (usuarioRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new FuncionarioJaExistenteException();
        }

        String senhaFuncionario = UUID.randomUUID().toString().substring(0, 4);

        String senhaCriptografada = passwordEncoder.encode(senhaFuncionario);


        Usuario funcionario = new Usuario(dto.nome(), dto.cpf(), dto.email(), dto.contato(), senhaCriptografada,
                RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);

        usuarioRepository.save(funcionario);
        emailService.enviarEmail(funcionario.getEmail(), "Dados para login", "Email: "
                + funcionario.getEmail() + " Senha: " + senhaFuncionario);

        CadastrarFuncionarioResponseDTO responseDTO = new CadastrarFuncionarioResponseDTO(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(), funcionario.getTelefone(), funcionario.getCpf());

        return ResponseEntity.ok().body(responseDTO);

    }
}
