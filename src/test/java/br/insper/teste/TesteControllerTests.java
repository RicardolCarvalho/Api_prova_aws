package br.insper.teste;

import br.insper.prova.teste.TesteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class TesteControllerTests {

    @InjectMocks
    private TesteController testecontroller;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(testecontroller)
                .build();
    }

    @Test
    void test_Teste() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teste"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}