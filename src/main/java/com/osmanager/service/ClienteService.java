package com.osmanager.service;

import com.osmanager.entity.Cliente;
import com.osmanager.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {

        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado"));
    }

    public void excluir(Long id) {

        clienteRepository.deleteById(id);
    }
}