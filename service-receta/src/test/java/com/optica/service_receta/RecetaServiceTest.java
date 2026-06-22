package com.optica.service_receta;

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
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.optica.service_receta.model.ConsultaDTO;
import com.optica.service_receta.model.Receta;
import com.optica.service_receta.repository.RecetaRepository;
import com.optica.service_receta.service.RecetaService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class RecetaServiceTest {
    @Mock
    private RecetaRepository recetaRepository;
    @Mock
    private WebClient.Builder webclientBuilder;
    @InjectMocks
    private RecetaService recetaService;

    @Test
    void obtenerRecetaCompletaTest(){
        // === ARRANGE ===
        Long recetaId = 1L;
        Long ConsultaId = 100L;

        Receta recetaMock = new Receta();
        recetaMock.setId(recetaId);
        recetaMock.setIdConsulta(ConsultaId);

        ConsultaDTO consultaMock = new ConsultaDTO();
        consultaMock.setId(ConsultaId);
        consultaMock.setDiagnostico("Miopia");
        
        WebClient webClient = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class); 
        

        when(webclientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(consultaMock));
        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(recetaMock));

        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(recetaMock));

        Receta resultado = recetaService.obtenerRecetaCompleto(recetaId);

        assertNotNull(resultado, "La receta retornada no debería ser nula");
        assertNotNull(resultado.getDatosConsulta(), "los datos de consulta deben estar presentes");

        ConsultaDTO datosRetornados = (ConsultaDTO) resultado.getDatosConsulta();
        assertEquals("Miopia", datosRetornados.getDiagnostico(), "El diagnostico debe coincidir con el simulado");
        assertEquals(ConsultaId, datosRetornados.getId());
        verify(recetaRepository, times(1)).findById(recetaId);
    }
}
