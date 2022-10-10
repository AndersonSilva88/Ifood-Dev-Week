package com.anderson.ifooddev.weekapi.service;

import com.anderson.ifooddev.weekapi.model.Item;
import com.anderson.ifooddev.weekapi.model.Sacola;
import com.anderson.ifooddev.weekapi.resource.dto.ItemDto;

public interface SacolaService {
    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
}
