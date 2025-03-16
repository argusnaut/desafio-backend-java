package br.com.argusnaut.desafio_backend_java.controller;

import br.com.argusnaut.desafio_backend_java.dto.FuncionarioDTO;
import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.service.FuncionarioService;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João Silva");
        funcionario.setCpf("111.222.333-44");
        funcionario.setEmail("joao.silva@test.com");
        funcionario.setSalario(new BigDecimal(5000));

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(UUID.randomUUID());
        funcionarioDTO.setNome("João Silva");

        when(funcionarioService.createFuncionario(any(Funcionario.class))).thenReturn(funcionarioDTO);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Test
    public void testGetAllFuncionarios() throws Exception {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(UUID.randomUUID());
        funcionarioDTO.setNome("Maria Souza");

        when(funcionarioService.getAllFuncionarios()).thenReturn(Collections.singletonList(funcionarioDTO));

        mockMvc.perform(get("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nome").value("Maria Souza"));
    }

    @Test
    public void testGetFuncionarioById_WhenFuncionarioExists() throws Exception {
        UUID id = UUID.randomUUID();
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(id);
        funcionarioDTO.setNome("Carlos Oliveira");

        when(funcionarioService.getFuncionarioById(id)).thenReturn(funcionarioDTO);

        mockMvc.perform(get("/funcionarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value("Carlos Oliveira"));
    }

    @Test
    public void testGetFuncionarioById_WhenFuncionarioDoesNotExist() throws Exception {
        UUID id = UUID.randomUUID();
        when(funcionarioService.getFuncionarioById(id)).thenReturn(null);

        mockMvc.perform(get("/funcionarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}