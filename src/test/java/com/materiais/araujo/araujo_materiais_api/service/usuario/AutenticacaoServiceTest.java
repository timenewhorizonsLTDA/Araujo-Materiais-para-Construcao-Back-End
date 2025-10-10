package com.materiais.araujo.araujo_materiais_api.service.usuario;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.*;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.*;
import com.materiais.araujo.araujo_materiais_api.infra.security.TokenService;
import com.materiais.araujo.araujo_materiais_api.model.usuario.CodigoAutorizacao;
import com.materiais.araujo.araujo_materiais_api.model.usuario.StatusUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.CodigoAutorizacaoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    //registro
    private CadastroDTO dto = new CadastroDTO("vitor", "70345324", "vitor@gmail.com", "123", "1234");
    private Usuario usuario = new Usuario();

    //validacao
    private CodigoAutorizacao codigoAutorizacao = new CodigoAutorizacao();
    private CodigoValidacaoDTO codigoValidacaoDTO = new CodigoValidacaoDTO("121212");

    //login
    private LoginDTO loginDTO = new LoginDTO("vitor@gmail.com", "123");

    @Test
    @DisplayName("Sucesso ao realizar cadastro")
    void cadastrarUsuarioCase1() {


        when(usuarioRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.empty());

        ResponseEntity<CadastroResponseDTO> resposta = autenticacaoService.cadastrarUsuario(dto);

        verify(usuarioRepository).save(any());
        verify(codigoAutorizacaoRepository).save(any());
        verify(emailService).enviarEmail(any(), any(), any());


    }

    @Test
    @DisplayName("Deve lancar EmailJaCadastradoException")
    void cadastrarUsuarioCase2() {

        when(usuarioRepository.findByEmail(any())).thenReturn(Optional.of(usuario));

        assertThrows(EmailJaCadastradoException.class, () -> autenticacaoService.cadastrarUsuario(dto));

        verify(usuarioRepository, never()).save(any());

    }

    @Test
    @DisplayName("Deve lancar CpfJaCadastradoExeception")
    void cadastrarUsuarioCase3() {

        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(usuario));

        assertThrows(CpfJaCadastradoExeception.class, () -> autenticacaoService.cadastrarUsuario(dto));

        verify(usuarioRepository, never()).save(any());

    }


    @Test
    @DisplayName("Sucesso ao validar usuario")
    void validarUsuariocase1() {

        Instant horaAtual = Instant.now();

        Instant horaExpiracao = horaAtual.plusMillis(600000);

        codigoAutorizacao.setHorarioDeEnvio(horaAtual);
        codigoAutorizacao.setHorarioDeExpiracao(horaExpiracao);

        codigoAutorizacao.setUsuario(usuario);
        codigoAutorizacao.getUsuario().setStatusUsuario(StatusUsuario.INATIVO);

        when(codigoAutorizacaoRepository.findByCodigo(any())).thenReturn(Optional.of(codigoAutorizacao));


        ResponseEntity<String> response = autenticacaoService.validarUsuario(codigoValidacaoDTO);


        assertTrue(response.getBody().contains("Validacao concluida"));
        verify(usuarioRepository).save(any());

    }

    @Test
    @DisplayName("Deve lancar CodigoDeValidacaoNaoValidoException")
    void validarUsuariocase2() {

        when(codigoAutorizacaoRepository.findByCodigo(any())).thenReturn(Optional.empty());


        assertThrows(CodigoDeValidacaoNaoValidoException.class, () -> autenticacaoService.validarUsuario(codigoValidacaoDTO));
        verify(usuarioRepository, never()).save(any());

    }

    @Test
    @DisplayName("Deve lancar CodigoDeValidacaoExpiradoException")
    void validarUsuariocase3() {

        Instant horaAtual = Instant.now();

        Instant horaExpiracao = horaAtual.minusMillis(600000);

        codigoAutorizacao.setHorarioDeEnvio(horaAtual);
        codigoAutorizacao.setHorarioDeExpiracao(horaExpiracao);

        codigoAutorizacao.setUsuario(usuario);
        codigoAutorizacao.getUsuario().setStatusUsuario(StatusUsuario.INATIVO);

        when(codigoAutorizacaoRepository.findByCodigo(any())).thenReturn(Optional.of(codigoAutorizacao));


        assertThrows(CodigoDeValidacaoExpiradoException.class, () -> autenticacaoService.validarUsuario(codigoValidacaoDTO));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Sucesso ao realizar login")
    void logincase1() {

        when(utilUsuario.obterUsuarioEmail(any())).thenReturn(usuario);

        usuario.setStatusUsuario(StatusUsuario.ATIVO);

        ResponseEntity<TokenDTO> resposta = autenticacaoService.login(loginDTO);

        verify(tokenService).gerarToken(any());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }

    @Test
    @DisplayName("Deve lancar UsuarioInativoException")
    void logincase2() {

        usuario.setStatusUsuario(StatusUsuario.INATIVO);

        when(utilUsuario.obterUsuarioEmail(any())).thenReturn(usuario);

        assertThrows(UsuarioInativoException.class, () -> autenticacaoService.login(loginDTO));
        verify(tokenService, never()).gerarToken(any());

    }

}