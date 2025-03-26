package br.insper.usuario;

import br.insper.prova.usuario.UsuarioDTO;
import br.insper.prova.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private RestTemplate restTemplate;

    private final String urlBase = "http://56.124.127.89:8080/api/usuario/";

    @Test
    void testValidarAdmin_ComPermissao() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("Ricardo");
        usuario.setEmail("ricardo@teste.com");
        usuario.setPapel("ADMIN");

        ResponseEntity<UsuarioDTO> response = new ResponseEntity<>(usuario, HttpStatus.OK);

        when(restTemplate.getForEntity(urlBase + "ricardo@teste.com", UsuarioDTO.class)).thenReturn(response);

        UsuarioDTO resultado = usuarioService.validarAdmin("ricardo@teste.com");

        assertEquals("Ricardo", resultado.getNome());
        assertEquals("ADMIN", resultado.getPapel());
    }

    @Test
    void testValidarAdmin_SemPermissao() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("User");
        usuario.setEmail("user@teste.com");
        usuario.setPapel("USER");

        ResponseEntity<UsuarioDTO> response = new ResponseEntity<>(usuario, HttpStatus.OK);

        when(restTemplate.getForEntity(urlBase + "user@teste.com", UsuarioDTO.class)).thenReturn(response);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.validarAdmin("user@teste.com");
        });
    }

    @Test
    void testValidarAdmin_UsuarioNaoExiste() {
        when(restTemplate.getForEntity(urlBase + "naoexiste@teste.com", UsuarioDTO.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.validarAdmin("naoexiste@teste.com");
        });

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void testValidarAdmin_Erro500DaApi() {
        when(restTemplate.getForEntity(urlBase + "user@teste.com", UsuarioDTO.class))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.validarAdmin("user@teste.com");
        });

        assertEquals(HttpStatus.FORBIDDEN, ex.getStatusCode());
    }

    @Test
    void testBuscarUsuario_Sucesso() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("Ricardo");
        usuario.setEmail("ricardo@teste.com");
        usuario.setPapel("ADMIN");

        ResponseEntity<UsuarioDTO> response = new ResponseEntity<>(usuario, HttpStatus.OK);

        when(restTemplate.getForEntity(urlBase + "ricardo@teste.com", UsuarioDTO.class)).thenReturn(response);

        UsuarioDTO resultado = usuarioService.buscarUsuario("ricardo@teste.com");

        assertEquals("Ricardo", resultado.getNome());
    }

    @Test
    void testBuscarUsuario_Erro() {
        when(restTemplate.getForEntity(urlBase + "naoexiste@teste.com", UsuarioDTO.class))
                .thenThrow(new RuntimeException("erro qualquer"));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.buscarUsuario("naoexiste@teste.com");
        });

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
