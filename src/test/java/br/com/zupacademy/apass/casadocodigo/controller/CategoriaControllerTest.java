package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.modelo.Categoria;
import br.com.zupacademy.apass.casadocodigo.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void quandoEnviadoUmaCategoriaCorretaFazOCadatro() throws Exception {
        this.categoriaRepository.deleteAll();

        final String request = "{\"nome\":\"Nova categoria\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/categoria")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }

    @Test
    public void quandoEnviadoUmaCategoriaSemONomeRetornaBadRequest() throws Exception {
        final String request = "{\"semNome\":\"Nova categoria\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/categoria")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void quandoEnviadoUmaCategoriaComNomeDuplicadoRetornaBadRequest() throws Exception {
        final String request = "{\"nome\":\"Nova categoria\"}";

        this.categoriaRepository.deleteAll();
        this.categoriaRepository.save(new Categoria("Nova categoria"));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/categoria")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
    }
}
