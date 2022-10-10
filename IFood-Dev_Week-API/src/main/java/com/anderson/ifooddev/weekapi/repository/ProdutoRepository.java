package com.anderson.ifooddev.weekapi.repository;

import com.anderson.ifooddev.weekapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
