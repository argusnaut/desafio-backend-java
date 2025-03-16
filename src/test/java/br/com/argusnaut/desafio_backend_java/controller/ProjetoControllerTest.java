package br.com.argusnaut.desafio_backend_java.controller;

import br.com.argusnaut.desafio_backend_java.dto.ProjetoDTO;
import br.com.argusnaut.desafio_backend_java.model.Projeto;
import br.com.argusnaut.desafio_backend_java.service.ProjetoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProjetoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjetoService projetoService;

    @InjectMocks
    private ProjetoController projetoController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(projetoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateProjeto() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto Teste");

        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(UUID.randomUUID());
        projetoDTO.setNome("Projeto Teste");

        when(projetoService.createProjeto(any(Projeto.class))).thenReturn(projetoDTO);

        String jsonProjeto = objectMapper.writeValueAsString(projeto);

        mockMvc.perform(post("/projetos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProjeto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Projeto Teste"));
    }

    @Test
    public void testGetAllProjetos() throws Exception {
        // Arrange
        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(UUID.randomUUID());
        projetoDTO.setNome("Projeto Teste");

        when(projetoService.getAllProjetos()).thenReturn(Collections.singletonList(projetoDTO));

        mockMvc.perform(get("/projetos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nome").value("Projeto Teste"));
    }

    @Test
    public void testGetProjetoById_WhenProjetoExists() throws Exception {
        UUID id = UUID.randomUUID();
        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(id);
        projetoDTO.setNome("Projeto Teste");

        when(projetoService.getProjetoById(id)).thenReturn(projetoDTO);

        mockMvc.perform(get("/projetos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value("Projeto Teste"));
    }

    @Test
    public void testGetProjetoById_WhenProjetoDoesNotExist() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        when(projetoService.getProjetoById(id)).thenReturn(null);

        mockMvc.perform(get("/projetos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}