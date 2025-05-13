package br.insper.config;

import br.insper.prova.config.AuthorizationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(AuthorizationConfig.class)
class AuthorizationConfigTests {

    @Autowired
    private MockMvc mvc;

    //
    // Definição de controllers de teste para exercitar as regras de URL
    //

    @RestController
    static class TestController {
        @GetMapping("/api/cursos/test")
        public String getCurso() { return "OK-GET"; }

        @PostMapping("/api/cursos/test")
        public String postCurso() { return "OK-POST"; }
    }

    @RestController
    static class PublicController {
        @GetMapping("/public/hello")
        public String hello() { return "HELLO"; }
    }

    //
    // Testes
    //

    @Test
    void getPublicWithoutAuth_shouldReturn200() throws Exception {
        mvc.perform(get("/public/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("HELLO"));
    }

    @Test
    void optionsOnApi_shouldReturn200() throws Exception {
        mvc.perform(options("/api/cursos/test")
                        .header("Origin", "http://foo.com")
                        .header("Access-Control-Request-Method", "POST"))
                .andExpect(status().isOk());
    }

    @Test
    void getApiWithoutAuth_shouldReturn401() throws Exception {
        mvc.perform(get("/api/cursos/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getApiWithJwt_shouldReturn200() throws Exception {
        mvc.perform(get("/api/cursos/test")
                        .with(jwt()))  // usuário genérico, sem role especial
                .andExpect(status().isOk())
                .andExpect(content().string("OK-GET"));
    }

    @Test
    void postApiWithAdminRole_shouldReturn200() throws Exception {
        mvc.perform(post("/api/cursos/test")
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("ROLE_ADMIN")
                        ))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("OK-POST"));
    }

    @Test
    void postApiWithUserRole_shouldReturn403() throws Exception {
        mvc.perform(post("/api/cursos/test")
                        .with(jwt().authorities(
                                // papel padrão, sem ROLE_ADMIN
                                new SimpleGrantedAuthority("ROLE_USER")
                        ))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
