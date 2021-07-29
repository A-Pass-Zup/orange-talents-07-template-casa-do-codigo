package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.modelo.Autor;
import br.com.zupacademy.apass.casadocodigo.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Encoding;
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

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void quandoEnviadoUmAutorCorretoFazOCadastro() throws Exception {

        URI uri = new URI("/api/v1/autor");


        String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"descricao\": \"Esta é a descrição do novo autor\"}";

        autorRepository.deleteAll();

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

        String json = "{\"nome\":\"Autor\", \"email\":\"invalido.email.com\", \"descricao\":\"Esta é a descrição do novo autor\"}";

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
    public void quandoEnviadoUmAutorComDescricaoInvalidaRetornaBadRequest() throws Exception {
        URI uri = new URI("/api/v1/autor");

        String json = "{\"nome\":\"Autor\", \"email\":\"email@email.com\", \"descricao\": \"\"}";

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
    public void quandoEnviadoUmAutorComEmailDuplicadoRetornaBadRequest() throws Exception {

        this.autorRepository.deleteAll();
        this.autorRepository.save(new Autor("Autor", "email@email.com", "Está é a descrição do autor."));

        String request = "{\"nome\":\"Autor\", \"email\":\"email@email.com\", \"descricao\": \"Esta é a descrição do novo autor\"}";
        String response = "[{\"field\":\"email\",\"message\":\"Já existe um(a) autor(a) com o e-mail informado!\"}]";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/autor")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
    }
}
