package com.example.veiculostock.controller;

import com.example.veiculostock.entity.Veiculo;
import com.example.veiculostock.service.VeiculoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoService service;
    public VeiculoController(VeiculoService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody Veiculo v){
        Veiculo created = service.create(v);
        return ResponseEntity.created(URI.create("/api/veiculos/" + created.getId())).body(created);
    }

    @GetMapping
    public Page<Veiculo> findAll(
            @RequestParam Optional<String> marca,
            @RequestParam Optional<String> modelo,
            @RequestParam Optional<BigDecimal> minPreco,
            @RequestParam Optional<BigDecimal> maxPreco,
            @RequestParam Optional<Integer> ano,
            @RequestParam Optional<String> status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ){
        String[] sortParts = sort.split(",");
        Sort s = Sort.by(Sort.Direction.fromString(sortParts[1]), sortParts[0]);
        return service.findWithFilter(marca, modelo, minPreco, maxPreco, ano, status, page, size, s);
    }

    @GetMapping("/{id}")
    public Veiculo get(@PathVariable Long id){ return service.findById(id); }

    @PutMapping("/{id}")
    public Veiculo update(@PathVariable Long id, @RequestBody Veiculo v){ return service.update(id, v); }

    @PatchMapping("/{id}")
    public Veiculo partialUpdate(@PathVariable Long id, @RequestBody Veiculo partial){
        // for simplicity reuse update (only numeric fields updated typically)
        Veiculo atual = service.findById(id);
        if(partial.getPreco() != null) atual.setPreco(partial.getPreco());
        if(partial.getQuilometragem() != null) atual.setQuilometragem(partial.getQuilometragem());
        if(partial.getStatus() != null) atual.setStatus(partial.getStatus());
        return service.update(id, atual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
