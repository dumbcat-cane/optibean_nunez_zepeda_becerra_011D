package com.optica.service_receta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_receta.model.Condicion;
import com.optica.service_receta.model.Receta;
import com.optica.service_receta.repository.CondicionRepository;
import com.optica.service_receta.repository.RecetaRepository;
             

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private CondicionRepository condicionRepository;
    public Receta guardarReceta(Receta receta){
        Receta guardado = recetaRepository.save(receta);

        Receta completo = recetaRepository.findById(guardado.getId())
                .orElse(guardado);
        return enriquecerConConsulta(completo);//ambas clases necesitan estar completas, no se necesita un if
    }

    public List<Receta> listarTodos(){

    List<Receta> recetas = recetaRepository.findAll();

    for(Receta receta : recetas){
        enriquecerConConsulta(receta);
    }

    return recetas;
}
    
    public Receta obtenerRecetaCompleto(Long id){
        Receta receta = recetaRepository.findById(id).orElse(null);
        if(receta != null){
            return enriquecerConConsulta(receta);
        }
        return null;
    }
    
    public Condicion guardarCondicion(Condicion condicion){
        return condicionRepository.save(condicion);
    }

    public List<Condicion> listarCondicion(){
        return condicionRepository.findAll();
    }

    public void eliminar(Long id){//elimina por id una receta
        recetaRepository.deleteById(id);
    }

    public Optional<Receta> actualizar(Long id, Receta recetaActualizada) {
    return recetaRepository.findById(id)
        .map(receta -> {
            receta.setGraduacionOd(recetaActualizada.getGraduacionOd());
            receta.setGraduacionOi(recetaActualizada.getGraduacionOi());
            receta.setCondicion(recetaActualizada.getCondicion());
            receta.setIdConsulta(recetaActualizada.getIdConsulta());
            return recetaRepository.save(receta);
        });
}

    private Receta enriquecerConConsulta(Receta receta){
        if(receta.getIdConsulta() != null){
            try{
                Object consulta = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8085/consultas/" + receta.getIdConsulta())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                receta.setDatosConsulta(consulta);
            }catch(Exception e){
                receta.setDatosConsulta(
                    "Informacion de la consulta no disponible"
                );
            }
        }
        return receta;
    }
        
    

}//fin
