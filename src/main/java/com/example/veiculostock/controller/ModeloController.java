package com.example.veiculostock.controller;

import com.example.veiculostock.entity.Modelo;
import com.example.veiculostock.service.ModeloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {
    private final ModeloService service;
    public ModeloController(ModeloService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Modelo> create(@RequestBody Modelo modelo){
        Modelo created = service.create(modelo);
        return ResponseEntity.created(URI.create("/api/modelos/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Modelo> list(){ return service.listAll(); }

    @GetMapping("/{id}")
    public Modelo get(@PathVariable Long id){ return service.findById(id); }

    @PutMapping("/{id}")
    public Modelo update(@PathVariable Long id, @RequestBody Modelo modelo){ return service.update(id, modelo); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
