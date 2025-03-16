package br.com.argusnaut.desafio_backend_java.service;

import br.com.argusnaut.desafio_backend_java.dto.ProjetoDTO;
import br.com.argusnaut.desafio_backend_java.exception.ProjetoNotFoundException;
import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.model.Projeto;
import br.com.argusnaut.desafio_backend_java.repository.FuncionarioRepository;
import br.com.argusnaut.desafio_backend_java.repository.ProjetoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @Test
    public void testCreateProjeto_WithExistingFuncionarios() {
        UUID funcionarioId = UUID.randomUUID();
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setNome("João Silva");

        Projeto projeto = new Projeto();
        projeto.setFuncionarios(Collections.singletonList(funcionario));

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionario));
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        ProjetoDTO result = projetoService.createProjeto(projeto);

        assertNotNull(result);
        assertEquals(1, result.getFuncionarios().size());
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    public void testCreateProjeto_WithNewFuncionarios() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Maria Souza");

        Projeto projeto = new Projeto();
        projeto.setFuncionarios(Collections.singletonList(funcionario));

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        ProjetoDTO result = projetoService.createProjeto(projeto);

        assertNotNull(result);
        assertEquals(1, result.getFuncionarios().size());
        verify(funcionarioRepository, times(1)).save(funcionario);
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    public void testGetAllProjetos() {
        Projeto projeto = new Projeto();
        projeto.setId(UUID.randomUUID());
        projeto.setNome("Projeto Teste");

        when(projetoRepository.findAll()).thenReturn(Collections.singletonList(projeto));

        List<ProjetoDTO> result = projetoService.getAllProjetos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(projeto.getId(), result.get(0).getId());
        assertEquals(projeto.getNome(), result.get(0).getNome());
        verify(projetoRepository, times(1)).findAll();
    }

    @Test
    public void testGetProjetoById_WhenProjetoExists() {
        UUID projetoId = UUID.randomUUID();
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        projeto.setNome("Projeto Teste");

        when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));

        ProjetoDTO result = projetoService.getProjetoById(projetoId);

        assertNotNull(result);
        assertEquals(projeto.getId(), result.getId());
        assertEquals(projeto.getNome(), result.getNome());
        verify(projetoRepository, times(1)).findById(projetoId);
    }

    @Test
    public void testGetProjetoById_WhenProjetoDoesNotExist() {
        UUID projetoId = UUID.randomUUID();
        when(projetoRepository.findById(projetoId)).thenReturn(Optional.empty());

        ProjetoNotFoundException exception = assertThrows(ProjetoNotFoundException.class, () -> {
            projetoService.getProjetoById(projetoId);
        });

        assertEquals("Projeto não encontrado com ID: " + projetoId, exception.getMessage());
        verify(projetoRepository, times(1)).findById(projetoId);
    }
}