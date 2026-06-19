package com.optica.service_consulta.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_consulta.model.Consulta;
import com.optica.service_consulta.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Consulta guardarConsulta(Consulta consulta){
        Consulta guardada = consultaRepository.save(consulta);
        Consulta completa = consultaRepository.findById(guardada.getId())
                .orElse(guardada);
        return enriquecerConCliente(completa);
    }

    public Consulta obtenerConsulta(Long id){

        Consulta consulta =
                consultaRepository.findById(id)
                .orElse(null);

        if(consulta != null){
            return enriquecerConCliente(consulta);
        }

        return null;
    }

    public void eliminar(Long id){
        consultaRepository.deleteById(id);
    }


    public List<Consulta> listarTodas(){
        List<Consulta> consultas = consultaRepository.findAll();
        for(Consulta consulta : consultas){
            enriquecerConCliente(consulta);
        }
        return consultas;
    }
    
    public Optional<Consulta> actualizar(Long id, Consulta consultaActualizada) {
    return consultaRepository.findById(id)
        .map(consulta -> {
            consulta.setFecha(consultaActualizada.getFecha());
            consulta.setDiagnostico(consultaActualizada.getDiagnostico());
            consulta.setClienteId(consultaActualizada.getClienteId());
            return consultaRepository.save(consulta);
        });
    }
    private Consulta enriquecerConCliente(
            Consulta consulta){

        if(consulta.getClienteId() != null){

            try{

                Object cliente =
                    webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/clientes/"
                        + consulta.getClienteId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

                consulta.setDatosCliente(cliente);

            }catch(Exception e){
                consulta.setDatosCliente(
                    "Informacion del cliente no disponible"
                );
            }
        }

        return consulta;
    }

}
