package com.anderson.ifooddev.weekapi.resource;

import com.anderson.ifooddev.weekapi.model.Item;
import com.anderson.ifooddev.weekapi.model.Sacola;
import com.anderson.ifooddev.weekapi.resource.dto.ItemDto;
import com.anderson.ifooddev.weekapi.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(value="/ifood-devweek/sacolas")
@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(sacolaId,formaPagamento);
    }


}
