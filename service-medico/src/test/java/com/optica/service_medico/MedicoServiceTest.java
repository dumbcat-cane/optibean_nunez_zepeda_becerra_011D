package com.optica.service_medico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_medico.model.ConsultaDTO;
import com.optica.service_medico.model.Medico;
import com.optica.service_medico.repository.MedicoRepository;
import com.optica.service_medico.service.MedicoService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;
    
    @Mock
    private WebClient.Builder webclientBuilder;
    
    @InjectMocks
    private MedicoService medicoService;

    @Test
    @DisplayName("Debería obtener un médico con su consulta externa de forma completa")
    void obtenerMedicoCompletoTest(){
     
        Long medicoId = 1L; 
        Long consultaId = 100L;

        Medico medicoMock = new Medico();
        medicoMock.setId(medicoId);
        medicoMock.setIdConsulta(consultaId);

        ConsultaDTO consultaMock = new ConsultaDTO();
        consultaMock.setId(consultaId);
        consultaMock.setDiagnostico("astigmatismo");

        WebClient webClient = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

     
        when(webclientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(consultaMock));

     
        when(medicoRepository.findById(medicoId)).thenReturn(Optional.of(medicoMock));

   
        Medico resultado = medicoService.obtenerMedicoCompleto(medicoId);

        assertNotNull(resultado, "El médico retornado no debería ser nulo");
        assertNotNull(resultado.getDatosConsulta(), "los datos de la consulta deben estar presentes");

        ConsultaDTO datosretornados = (ConsultaDTO) resultado.getDatosConsulta();
        assertEquals("astigmatismo", datosretornados.getDiagnostico(), "el diagnostico debe coincidir con el simulado");
        assertEquals(consultaId, datosretornados.getId());

        verify(medicoRepository, times(1)).findById(medicoId);
    }
}
