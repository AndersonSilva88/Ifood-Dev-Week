package com.anderson.ifooddev.weekapi.repository;

import com.anderson.ifooddev.weekapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
