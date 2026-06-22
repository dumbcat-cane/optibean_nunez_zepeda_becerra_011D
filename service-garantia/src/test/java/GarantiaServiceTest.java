import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_garantia.model.Garantia;
import com.optica.service_garantia.model.VentaDTO;
import com.optica.service_garantia.repository.GarantiaRepository;
import com.optica.service_garantia.service.GarantiaService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class GarantiaServiceTest {

    @Mock
    private GarantiaRepository garantiaRepository;
    @Mock
    private WebClient.Builder webclientBuilder;
    @InjectMocks
    private GarantiaService garantiaService;
    @Test
    void obtenerRecetaCompletaTest(){
        Long garantiaId = 1L;
        Long VentaId = 100l;

        Garantia garantiaMock = new Garantia();
        garantiaMock.setId(garantiaId);
        garantiaMock.setIdventa(VentaId);

        VentaDTO ventaMock= new VentaDTO();
        ventaMock.setId(VentaId);
        ventaMock.setMontofecha(25000);
        WebClient webClient = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        
        when(webclientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);

        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(ventaMock));
        when(garantiaRepository.findById(garantiaId)).thenReturn(Optional.of(garantiaMock));

        Garantia resultado = garantiaService.obtenerGarantiaCompleta(garantiaId);
        assertNotNull(resultado.getDatosVenta(),"los datos de venta deben estar presentes");
        
        VentaDTO datosretornados=(VentaDTO) resultado.getDatosVenta();
        assertEquals(25000,datosretornados.getMontofecha(),"el monto deberia coincidir con el simulado");
        assertEquals(VentaId, datosretornados.getId());
        verify(garantiaRepository,times(1)).findById(garantiaId);

        
    }

}
