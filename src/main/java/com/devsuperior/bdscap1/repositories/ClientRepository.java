package com.devsuperior.bdscap1.repositories;

import com.devsuperior.bdscap1.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
