package com.optica.service_estadisticas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_estadisticas.model.Estadisticas;
import com.optica.service_estadisticas.repository.EstadisticasRepository;




@Service
public class EstadisticasService {

    @Autowired
    private EstadisticasRepository estadisticasRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Estadisticas guardarEstadistica(Estadisticas estadisticas){
        Estadisticas guardado = estadisticasRepository.save(estadisticas);

        Estadisticas completo = estadisticasRepository.findById(guardado.getId())
                .orElse(guardado);
        return enriquecerConInventario(completo);//ambas clases necesitan estar completas, no se necesita un if
    }


    public List<Estadisticas> listarTodos(){

    List<Estadisticas> estadistica = estadisticasRepository.findAll();

    for(Estadisticas estadisticas : estadistica){
        enriquecerConInventario(estadisticas);
    }
    return estadistica;
}

    public void eliminar(Long id){
        estadisticasRepository.deleteById(id);
    }


    public Optional<Estadisticas> actualizar(Long id, Estadisticas estadisticaActualizada) {
    return estadisticasRepository.findById(id)
        .map(estadisticas -> {
            estadisticas.setNombreReporte(estadisticaActualizada.getNombreReporte());
            estadisticas.setObservacion(estadisticaActualizada.getObservacion());
            return estadisticasRepository.save(estadisticas);
        });
        
}

    public Estadisticas obtenerEstadisticaCompleta(Long id){
        Estadisticas est = estadisticasRepository.findById(id).orElse(null);
        if(est != null){
            return enriquecerConInventario(est);
        }
        return null;
    }



    private Estadisticas enriquecerConInventario(Estadisticas estad){
        if(estad.getIdLente() !=null){
            try{
                Object invetario = webClientBuilder.build()
                .get()
                .uri("http//localhost:8084/inventario/" + estad.getIdLente())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
            estad.setDatoslente(invetario);
            }catch(Exception e){
                estad.setDatoslente(
                    "Informacion del inventario no disponible"
                );
            }
        }
        return estad;
    }
    



}//fin
