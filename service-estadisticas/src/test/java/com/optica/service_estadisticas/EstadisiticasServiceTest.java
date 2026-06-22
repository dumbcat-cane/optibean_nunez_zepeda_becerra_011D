package com.optica.service_estadisticas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_estadisticas.model.Estadisticas;
import com.optica.service_estadisticas.model.LenteDTO;
import com.optica.service_estadisticas.repository.EstadisticasRepository;
import com.optica.service_estadisticas.service.EstadisticasService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class EstadisiticasServiceTest {

    @Mock
    private EstadisticasRepository estadisticasRepository;
    @Mock
    private WebClient.Builder webclientBuilder;
    @InjectMocks
    private EstadisticasService estadisticasService;
    @Test
    void obtenerMedicoCompletoTest(){
        Long estadisticasId=1l;
        Long lenteId=100L;

        Estadisticas estadisticasMock = new Estadisticas();
        estadisticasMock.setId(estadisticasId);
        estadisticasMock.setIdLente(lenteId);

        LenteDTO lenteMock = new LenteDTO();
        lenteMock.setId(lenteId);
        lenteMock.setCodigo("PG9018");

        WebClient webClient = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        
        when(webclientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);

        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(lenteMock));

        Estadisticas resultado = estadisticasService.obtenerEstadisticaCompleta(estadisticasId);
        assertNotNull(resultado.getDatoslente(),"los datos de los lentes deben estar presentes");

        LenteDTO datosretornados =(LenteDTO) resultado.getDatoslente();
        assertEquals("PG9018",datosretornados.getCodigo(), "el codigo debe coincidir con el simulado");
        assertEquals(lenteId, datosretornados.getId());
        verify(estadisticasRepository, times(1)).findById(estadisticasId);


    }//fin

}
