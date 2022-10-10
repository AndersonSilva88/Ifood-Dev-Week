package com.anderson.ifooddev.weekapi.repository;

import com.anderson.ifooddev.weekapi.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
}
