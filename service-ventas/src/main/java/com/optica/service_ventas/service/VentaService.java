package com.optica.service_ventas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.optica.service_ventas.model.Venta;
import com.optica.service_ventas.repository.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Venta guardarVenta(Venta venta){
        Venta guardada = ventaRepository.save(venta);
        return enriquecerConClienteYLente(guardada);
    }

    public List<Venta> listarTodos(){

        List<Venta> ventas = ventaRepository.findAll();
        for(Venta venta : ventas){
            enriquecerConClienteYLente(venta);
        }
        return ventas;
    }

    public Venta obtenerVentaCompleta(Long id){

        Venta venta = ventaRepository.findById(id).orElse(null);
        if(venta != null){
            return enriquecerConClienteYLente(venta);
        }
        return null;
    }

    public void eliminar(Long id){
        ventaRepository.deleteById(id);
    }

    public Optional<Venta> actualizar(Long id, Venta ventaActualizada){

        return ventaRepository.findById(id)
            .map(venta -> {

                venta.setFechaVenta(ventaActualizada.getFechaVenta());
                venta.setMonto(ventaActualizada.getMonto());
                venta.setMetodoPago(ventaActualizada.getMetodoPago());
                venta.setIdCliente(ventaActualizada.getIdCliente());
                venta.setIdLente(ventaActualizada.getIdLente());

                return ventaRepository.save(venta);
            });
    }
   
//Conexiooooooon con ambos microservicios cliente y lente
    private Venta enriquecerConClienteYLente(Venta venta){

        if(venta.getIdCliente() != null){

            try{

                Object cliente =
                    webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/clientes/" + venta.getIdCliente())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

                venta.setDatosCliente(cliente);

            }catch(Exception e){

                venta.setDatosCliente(
                    "Informacion del cliente no disponible"
                );
            }
        }

        if(venta.getIdLente() != null){

            try{

                Object lente =
                    webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8084/api/v1/lentes/"
                        + venta.getIdLente())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

                venta.setDatosLente(lente);

            }catch(Exception e){

                venta.setDatosLente(
                    "Informacion del lente no disponible"
                );
            }
        }

        return venta;
    }
}
