package com.optica.service_cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.service_cliente.model.Cliente;
import com.optica.service_cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente>listarTodos(){
        return clienteRepository.findAll();
    }
    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {
    return clienteRepository.findById(id)
        .map(cliente -> {
            cliente.setRun(clienteActualizado.getRun());
            cliente.setNombres(clienteActualizado.getNombres());
            cliente.setApellidos(clienteActualizado.getApellidos());
            return clienteRepository.save(cliente);
        });
    }
    public void eliminar(Long id){
        clienteRepository.deleteById(id);
    }

}
