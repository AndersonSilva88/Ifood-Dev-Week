package com.anderson.ifooddev.weekapi.service.impl;

import com.anderson.ifooddev.weekapi.enumeration.FormaPagamento;
import com.anderson.ifooddev.weekapi.model.Item;
import com.anderson.ifooddev.weekapi.model.Restaurante;
import com.anderson.ifooddev.weekapi.model.Sacola;
import com.anderson.ifooddev.weekapi.repository.ItemRepository;
import com.anderson.ifooddev.weekapi.repository.ProdutoRepository;
import com.anderson.ifooddev.weekapi.repository.SacolaRepository;
import com.anderson.ifooddev.weekapi.resource.dto.ItemDto;
import com.anderson.ifooddev.weekapi.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {

        Sacola sacola = verSacola(itemDto.getSacolaId());

        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola está fechada");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esse produto não existe!");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possivel adicionar produtos de restaurantes diferentes." +
                        "Feche a sacola ou esvazie");
            }
        }

        List<Double> valorDosItens = new ArrayList<>();
        for (Item itemDaSacola : itensDaSacola) {
            double valorTotalItem =
            itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);
        return itemRepository.save(itemParaSerInserido);
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Sacola não existe!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroformaPagamento) {
        Sacola sacola = verSacola(id);

        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola!");
        }

        FormaPagamento formaPagamento =
                numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.CARTAO;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);

    }
}
