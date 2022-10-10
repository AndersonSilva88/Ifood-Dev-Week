package com.anderson.ifooddev.weekapi.repository;

import com.anderson.ifooddev.weekapi.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
