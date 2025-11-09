package com.materiais.araujo.araujo_materiais_api.service.gerente;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.*;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.FuncionarioJaExistenteException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.FuncionarioNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.SenhaInvalidaException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.StatusUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.usuario.EmailService;
import com.materiais.araujo.araujo_materiais_api.service.usuario.UtilUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GerenteService {

    private UsuarioRepository usuarioRepository;

    private EmailService emailService;

    private PasswordEncoder passwordEncoder;

    private UtilUsuario utilUsuario;

    public GerenteService(UsuarioRepository usuarioRepository, EmailService emailService, PasswordEncoder passwordEncoder, UtilUsuario utilUsuario) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.utilUsuario = utilUsuario;
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

    public ResponseEntity<EditarFuncionarioResponseDTO> editarFuncionario(Integer idFuncionario, EditarFuncionarioDTO dto) {

        Usuario funcionario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new FuncionarioNaoEncontradoException());

        if (funcionario.getRole() != RoleUsuario.FUNCIONARIO) {
            throw new FuncionarioNaoEncontradoException();
        }

        if (!dto.nome().isEmpty()) {
            funcionario.setNome(dto.nome());
        }

        if (!dto.email().isEmpty()) {
            funcionario.setEmail(dto.email());
        }

        if (!dto.contato().isEmpty()) {
            funcionario.setTelefone(dto.contato());
        }

        usuarioRepository.save(funcionario);

        EditarFuncionarioResponseDTO responseDTO = new EditarFuncionarioResponseDTO(funcionario.getId(), funcionario.getNome(),
                funcionario.getEmail(), funcionario.getTelefone());

        return ResponseEntity.ok().body(responseDTO);


    }

    public ResponseEntity<List<BuscarFuncionarioDTO>> buscarTodosFuncionarios() {

        List<Usuario> usuarios = usuarioRepository.findAllByRole(RoleUsuario.FUNCIONARIO);

        List<BuscarFuncionarioDTO> usuariosDTO = usuarios.stream().map(e -> new BuscarFuncionarioDTO(e.getId(), e.getNome(), e.getEmail(), e.getTelefone())).toList();

        return ResponseEntity.ok().body(usuariosDTO);


    }


    public ResponseEntity<List<BuscarFuncionarioDTO>> buscarFuncionariosPorNome(BuscarFuncionarioNomeDTO dto) {

        List<Usuario> usuarios = usuarioRepository.findByRoleAndNomeContainingIgnoreCase(RoleUsuario.FUNCIONARIO, dto.nome());

        List<BuscarFuncionarioDTO> usuariosDTO = usuarios.stream().map(e -> new BuscarFuncionarioDTO(e.getId(), e.getNome(),
                e.getEmail(), e.getTelefone())).toList();

        return ResponseEntity.ok().body(usuariosDTO);
    }

    public void deletarFuncionario(SenhaDTO senhaGerente, Integer idFuncionario) {

        Usuario gerente = utilUsuario.obterUsuarioDaVez();

        if (!passwordEncoder.matches(senhaGerente.senha(), gerente.getSenha())) {
            throw new SenhaInvalidaException();
        }

        Usuario funcionario = usuarioRepository.findByIdAndRole(idFuncionario, RoleUsuario.FUNCIONARIO)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException());

        usuarioRepository.delete(funcionario);

    }


}
