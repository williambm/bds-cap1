package com.devsuperior.bdscap1.resources;

import com.devsuperior.bdscap1.dto.ClientDTO;
import com.devsuperior.bdscap1.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/clients")
public class ClientResource {

    @Autowired
    private ClientService repository;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO dto = repository.findById(id);
        return ResponseEntity.ok(dto);
    }
}
