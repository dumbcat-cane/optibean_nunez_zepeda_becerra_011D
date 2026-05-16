package com.optica.service_inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_inventario.model.Lente;
import com.optica.service_inventario.model.TipoMaterial;
import com.optica.service_inventario.repository.LenteRepository;
import com.optica.service_inventario.repository.TipoMaterialRepository;

@Service
public class InventarioService {
    @Autowired
    private LenteRepository lenteRepository;
    @Autowired
    private WebClient.Builder webClienBuilder;
    @Autowired
    private TipoMaterialRepository tipoMaterialRepository;
    public Lente guardarLente (Lente lente){
        Lente guardado = lenteRepository.save(lente);

        Lente completo = lenteRepository.findById(guardado.getId())
                .orElse(guardado);
        return enriquecerConProveedor(completo);
    }
    public List<Lente> listarTodos(){
        return lenteRepository.findAll();
    }
    public Lente obtenerLenteCompleto(Long id){
        Lente lente = lenteRepository.findById(id).orElse(null);
        if(lente != null){
            return enriquecerConProveedor(lente);
        }
        return null;
    }

    public void eliminar(Long id){
        lenteRepository.deleteById(id);
    }

    public Optional<Lente> actualizar(Long id, Lente inventarioActualizado) {
    return lenteRepository.findById(id)
        .map(lente -> {
            lente.setCodigo(inventarioActualizado.getCodigo());
            lente.setColor(inventarioActualizado.getColor());
            lente.setCantidad(inventarioActualizado.getCantidad());
            lente.setIdProveedor(inventarioActualizado.getIdProveedor());
            return lenteRepository.save(lente);
        });
    }

    public TipoMaterial guardarTipoMaterial(TipoMaterial tipoMaterial){
        return tipoMaterialRepository.save(tipoMaterial);
    }
    public List<TipoMaterial> listarTipoMaterial(){
        return tipoMaterialRepository.findAll();
    }

    //CONEXIOOOOOOOON

    private Lente enriquecerConProveedor(Lente lente){
        if(lente.getIdProveedor() != null){
            try{
                Object proveedor = webClienBuilder.build()
                    .get()
                    .uri("http://localhost:8083/proveedor/" + lente.getIdProveedor())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                lente.setDatosProveedor(proveedor);
            }catch(Exception e){
                lente.setDatosProveedor(
                    "Informacion del proveedor no disponible"
                );
            }
        }
        return lente;
    }

}
