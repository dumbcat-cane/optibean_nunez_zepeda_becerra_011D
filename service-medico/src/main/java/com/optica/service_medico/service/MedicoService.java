package com.optica.service_medico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_medico.model.Medico;
import com.optica.service_medico.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Medico guardarMedico(Medico medico){

        Medico guardado = medicoRepository.save(medico);

        Medico completo = medicoRepository.findById(guardado.getId())
                .orElse(guardado);

        return enriquecerConConsulta(completo);
    }

    public List<Medico> listarTodos(){
        List<Medico> medicos = medicoRepository.findAll();
        for(Medico medico : medicos){
            enriquecerConConsulta(medico);
        }
        return medicos;
    }

    public Medico obtenerMedicoCompleto(Long id){

        Medico medico = medicoRepository.findById(id).orElse(null);
        if(medico != null){
            return enriquecerConConsulta(medico);
        }
        return null;
    }

    public void eliminar(Long id){
        medicoRepository.deleteById(id);
    }

    public Optional<Medico> actualizar(Long id, Medico medicoActualizado){

        return medicoRepository.findById(id)
            .map(medico -> {

                medico.setRun(medicoActualizado.getRun());
                medico.setNombre(medicoActualizado.getNombre());
                medico.setTelefono(medicoActualizado.getTelefono());
                medico.setCorreo(medicoActualizado.getCorreo());
                medico.setEspecialidad(medicoActualizado.getEspecialidad());
                medico.setIdConsulta(medicoActualizado.getIdConsulta());

                return medicoRepository.save(medico);
            });
    }

    private Medico enriquecerConConsulta(Medico medico){

        if(medico.getIdConsulta() != null){
            try{
                Object consulta =
                    webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8085/consultas/"
                        + medico.getIdConsulta())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                medico.setDatosConsulta(consulta);
            }catch(Exception e){

                medico.setDatosConsulta(
                    "Informacion de la consulta no disponible"
                );
            }
        }

        return medico;
    }
}
