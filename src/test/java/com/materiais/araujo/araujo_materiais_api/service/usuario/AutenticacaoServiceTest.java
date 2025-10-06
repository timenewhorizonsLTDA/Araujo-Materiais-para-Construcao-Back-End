package com.materiais.araujo.araujo_materiais_api.service.usuario;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CadastroDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.CpfJaCadastradoExeception;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.EmailJaCadastradoException;
import com.materiais.araujo.araujo_materiais_api.infra.security.TokenService;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.CodigoAutorizacaoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CodigoAutorizacaoRepository codigoAutorizacaoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private EmailService emailService;

    @Mock
    private UtilUsuario utilUsuario;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    private CadastroDTO dto = new CadastroDTO("vitor", "70345324", "vitor@gmail.com", "123");
    private Usuario usuario = new Usuario();

    @Test
    @DisplayName("Sucesso ao realizar cadastro")
    void cadastrarUsuarioCase1() {


        when(usuarioRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.empty());

        ResponseEntity<String> resposta = autenticacaoService.cadastrarUsuario(dto);

        verify(usuarioRepository).save(any());
        verify(codigoAutorizacaoRepository).save(any());
        verify(emailService).enviarEmail(any(), any(), any());

        assertTrue(resposta.getBody().contains("Cadastro realizado com sucesso"));

    }

    @Test
    @DisplayName("Deve lancar uma EmailJaCadastradoException")
    void cadastrarUsuarioCase2() {

        when(usuarioRepository.findByEmail(any())).thenReturn(Optional.of(usuario));

        assertThrows(EmailJaCadastradoException.class, () -> autenticacaoService.cadastrarUsuario(dto));

        verify(usuarioRepository, never()).save(any());

    }

    @Test
    @DisplayName("Deve lancar uma CpfJaCadastradoExeception")
    void cadastrarUsuarioCase3() {

        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(usuario));

        assertThrows(CpfJaCadastradoExeception.class, () -> autenticacaoService.cadastrarUsuario(dto));

        verify(usuarioRepository, never()).save(any());

    }

}