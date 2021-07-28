package br.com.zupacademy.apass.casadocodigo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@ActiveProfiles("dev")
@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void quandoEnviadoUmAutorCorretoFazOCadastro() throws Exception {

        URI uri = new URI("/api/v1/autor");

        String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"descricao\": \"Esta é a descrição do novo autor\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers
                    .status().is(HttpStatus.OK.value())
        );
    }

    @Test
    public void quandoEnviadoUmAutorComNomeInvalidoRetornaBadRequest() throws Exception {
        URI uri = new URI("/api/v1/autor");

        String json = "{\"nome\":\"\", \"email\":\"email@email.com\", \"descricao\": \"Esta é a descrição do novo autor\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers
                        .status().is(HttpStatus.BAD_REQUEST.value())
        );
    }

    @Test
    public void quandoEnviadoUmAutorComEmailInvalidoRetornaErrorValidacao() throws Exception {
        URI uri = new URI("/api/v1/autor");

        String json = "{\"nome\":\"Autor\", \"email\":\"invalido.email.com\", \"descricao\": \"Esta é a descrição do novo autor\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers
                        .status().is(HttpStatus.BAD_REQUEST.value())
        );
    }

    public void quandoEnviadoUmAutorComDescricaoInvalidaRetornaBadRequest() throws Exception {
        URI uri = new URI("/api/v1/autor");

        String json = "{\"nome\":\"Autor\", \"email\":\"invalido.email.com\", \"descricao\": \"\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers
                        .status().is(HttpStatus.BAD_REQUEST.value())
        );
    }
}
