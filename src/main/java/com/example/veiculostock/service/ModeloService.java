package com.example.veiculostock.service;

import com.example.veiculostock.entity.Modelo;
import com.example.veiculostock.entity.Marca;
import com.example.veiculostock.repository.ModeloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {
    private final ModeloRepository modeloRepository;
    public ModeloService(ModeloRepository modeloRepository){ this.modeloRepository = modeloRepository; }

    public Modelo create(Modelo m){ return modeloRepository.save(m); }
    public List<Modelo> listAll(){ return modeloRepository.findAll(); }
    public Modelo findById(Long id){ return modeloRepository.findById(id).orElseThrow(() -> new RuntimeException("Modelo não encontrado")); }
    public Modelo update(Long id, Modelo dados){
        Modelo md = findById(id);
        md.setNome(dados.getNome());
        md.setMarca(dados.getMarca());
        return modeloRepository.save(md);
    }
    public void delete(Long id){ modeloRepository.deleteById(id); }
}
