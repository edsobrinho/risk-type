package com.zagwork.risktype.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zagwork.risktype.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);

}
