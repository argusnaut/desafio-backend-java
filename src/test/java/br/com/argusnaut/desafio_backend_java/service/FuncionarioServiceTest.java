package br.com.argusnaut.desafio_backend_java.service;

import br.com.argusnaut.desafio_backend_java.dto.FuncionarioDTO;
import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repository;

    @InjectMocks
    private FuncionarioService service;

    @Test
    public void testCreateFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(UUID.randomUUID());
        funcionario.setNome("João Silva");
        funcionario.setCpf("11122233344");

        when(repository.save(any(Funcionario.class))).thenReturn(funcionario);

        FuncionarioDTO result = service.createFuncionario(funcionario);

        assertNotNull(result);
        assertEquals(funcionario.getId(), result.getId());
        assertEquals(funcionario.getNome(), result.getNome());
        assertEquals("111.222.333-44", funcionario.getCpf());
        verify(repository, times(1)).save(funcionario);
    }

    @Test
    public void testGetAllFuncionarios() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(UUID.randomUUID());
        funcionario.setNome("Maria Souza");
        funcionario.setCpf("111.222.333-44");

        when(repository.findAll()).thenReturn(Collections.singletonList(funcionario));

        List<FuncionarioDTO> result = service.getAllFuncionarios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(funcionario.getId(), result.getFirst().getId());
        assertEquals(funcionario.getNome(), result.getFirst().getNome());
        assertEquals(funcionario.getCpf(), result.getFirst().getCpf());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetFuncionarioById_WhenFuncionarioExists() {
        UUID id = UUID.randomUUID();
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("Carlos Oliveira");

        when(repository.findById(id)).thenReturn(Optional.of(funcionario));

        FuncionarioDTO result = service.getFuncionarioById(id);

        assertNotNull(result);
        assertEquals(funcionario.getId(), result.getId());
        assertEquals(funcionario.getNome(), result.getNome());
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testGetFuncionarioById_WhenFuncionarioDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        FuncionarioDTO result = service.getFuncionarioById(id);

        assertNull(result);
        verify(repository, times(1)).findById(id);
    }
}
