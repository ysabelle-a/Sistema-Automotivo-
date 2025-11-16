package com.example.veiculostock.controller;

import com.example.veiculostock.entity.Marca;
import com.example.veiculostock.service.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    private final MarcaService service;
    public MarcaController(MarcaService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Marca> create(@RequestBody Marca marca){
        Marca created = service.create(marca);
        return ResponseEntity.created(URI.create("/api/marcas/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Marca> list(){ return service.listAll(); }

    @GetMapping("/{id}")
    public Marca get(@PathVariable Long id){ return service.findById(id); }

    @PutMapping("/{id}")
    public Marca update(@PathVariable Long id, @RequestBody Marca marca){ return service.update(id, marca); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
