package com.anderson.ifooddev.weekapi.repository;

import com.anderson.ifooddev.weekapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
