package com.materiais.araujo.araujo_materiais_api.service.gerente;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.FuncionarioJaExistenteException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.usuario.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenteServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private GerenteService gerenteService;


    @Test
    @DisplayName("Sucesso ao cadastrar funcionario")
    void cadastrarFuncionarioCase1() {

        CadastrarFuncionarioDTO dto = new CadastrarFuncionarioDTO("vitor", "1212121", "dsdsdsdsds");

        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.empty());

        gerenteService.cadastrarFuncionario(dto);

        verify(usuarioRepository).save(any());
        verify(emailService).enviarEmail(any(), any(), any());
        verify(passwordEncoder).encode(any());

    }

    @Test
    @DisplayName("Deve lancar FuncionarioJaExistenteException")
    void cadastrarFuncionarioCase2(){

        Usuario funcionario = new Usuario();
        funcionario.setCpf("772737374");

        CadastrarFuncionarioDTO dto = new CadastrarFuncionarioDTO("vitor", funcionario.getCpf(), "dsdsdsdsds");

        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(funcionario));

        assertThrows(FuncionarioJaExistenteException.class, () -> gerenteService.cadastrarFuncionario(dto));

        verify(emailService, never()).enviarEmail(any(),any(),any());
        verify(usuarioRepository, never()).save(any());

    }
}