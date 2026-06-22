package com.optica.service_proveedor;

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

import com.optica.service_proveedor.model.Proveedor;
import com.optica.service_proveedor.repository.Proveedorrepository;
import com.optica.service_proveedor.service.ProveedorService;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {
    @Mock
    private Proveedorrepository proveedorrepository;
    @InjectMocks
    private ProveedorService proveedorService;
    @Test
    @DisplayName("Deberia guardar a un proveedor correctamente")
    void guardarproveedortest(){
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("lyu limitada");
        proveedor.setRun("76607348-4");

    when(proveedorrepository.save(any(Proveedor.class)))
            .thenAnswer(invocation -> {
                Proveedor p = invocation.getArgument(0);
                p.setId(1L);
                return p;
            });
    Proveedor resultado = proveedorService.guardar(proveedor);
    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals("lyu limitada", resultado.getNombre());

    verify(proveedorrepository, times(1)).save(proveedor);    

}
}
