package com.devsuperior.bdscap1.services;

import com.devsuperior.bdscap1.dto.ClientDTO;
import com.devsuperior.bdscap1.entities.Client;
import com.devsuperior.bdscap1.repositories.ClientRepository;
import com.devsuperior.bdscap1.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> entity = repository.findById(id);

        Client client = entity.orElseThrow(()-> new ResourceNotFoundException("ID Not Found: "+id));
        return new ClientDTO(client);
    }
}
