package com.optica.service_garantia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_garantia.model.Garantia;
import com.optica.service_garantia.repository.GarantiaRepository;

@Service

public class GarantiaService {

    @Autowired
    private GarantiaRepository garantiaRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    public Garantia guardGarantia(Garantia garantia){
        Garantia guardado = garantiaRepository.save(garantia);

        Garantia completo = garantiaRepository.findById(guardado.getId())
                .orElse(guardado);
        return enriquecerConVenta(completo);
    }

    public List<Garantia> listarTodos(){

    List<Garantia> garantias = garantiaRepository.findAll();

    for(Garantia garantia : garantias){
        enriquecerConVenta(garantia);
    }

    return garantias;
}


    public Garantia obtenerGarantiaCompleta(Long id){
        Garantia garantia = garantiaRepository.findById(id).orElse(null);
        if(garantia != null){
            return enriquecerConVenta(garantia);
        }
        return null;
    }

    public void eliminar(Long id){
        garantiaRepository.deleteById(id);
    }


    public Optional<Garantia> actualizar(Long id, Garantia garantiaActualizada){
        return garantiaRepository.findById(id)
            .map(garantia -> {
                garantia.setMotivo(garantiaActualizada.getMotivo());
                garantia.setEstado(garantiaActualizada.getEstado());
                garantia.setFechasolicitud(garantiaActualizada.getFechasolicitud());  
                garantia.setResolucion(garantiaActualizada.getResolucion());
            return garantiaRepository.save(garantia);
        });
}











    private Garantia enriquecerConVenta(Garantia garantia){
        if(garantia.getIdventa() !=null){
            try{
                Object venta =webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8087/ventas/" + garantia.getIdventa())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                garantia.setDatosVenta(venta);

            }catch(Exception e){
                garantia.setDatosVenta(
                    "Informacion de la venta no disponible"
                );
            }
        }
        return garantia;
    }
}//final
