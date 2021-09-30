package com.devsuperior.bdscap1.services;

import com.devsuperior.bdscap1.dto.ClientDTO;
import com.devsuperior.bdscap1.entities.Client;
import com.devsuperior.bdscap1.repositories.ClientRepository;
import com.devsuperior.bdscap1.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(PageRequest pageRequest) {
        Page<Client> clients = repository.findAll(pageRequest);
        return clients.map(entity -> new ClientDTO(entity));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> entity = repository.findById(id);

        Client client = entity.orElseThrow(() -> new ResourceNotFoundException("ID Not Found: " + id));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Client entity = modelMapper.map(dto, Client.class);
        entity = repository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client actualEntity = repository.getOne(id);
            BeanUtils.copyProperties(dto, actualEntity, "id");
            actualEntity = repository.save(actualEntity);
            return new ClientDTO(actualEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("It´s not possible update the register for id: " + id + " maybe this resource dosen´t exists");
        } catch (BeansException e) {
            throw new ResourceNotFoundException("It´s not possible update the register for id: " + id + " maybe this resource dosen´t exists");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Not found Id: " + id + " for exclusion.");
        }
    }
}
