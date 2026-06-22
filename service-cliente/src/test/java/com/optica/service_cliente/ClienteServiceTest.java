package com.optica.service_cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.optica.service_cliente.model.Cliente;
import com.optica.service_cliente.repository.ClienteRepository;
import com.optica.service_cliente.service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;
    @Test
    @DisplayName("Deberia guardar a un cliente correctamente")
    void guardarclientetest(){
        Cliente cliente = new Cliente();
        cliente.setNombres("Charlie bradbury");
        cliente.setRun("14966343-4");

    when(clienteRepository.save(any(Cliente.class)))
            .thenAnswer(invocation -> {
                Cliente p = invocation.getArgument(0);
                p.setId(1L);
                return p;
            });
    Cliente resultado = clienteService.guardar(cliente);
    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals("Charlie bradbury", resultado.getNombres());
    verify(clienteRepository,times(1)).save(cliente);
    }

}
