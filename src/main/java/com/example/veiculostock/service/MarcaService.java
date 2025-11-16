package com.example.veiculostock.service;

import com.example.veiculostock.entity.Marca;
import com.example.veiculostock.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;
    public MarcaService(MarcaRepository marcaRepository){ this.marcaRepository = marcaRepository; }

    public Marca create(Marca marca){ return marcaRepository.save(marca); }
    public List<Marca> listAll(){ return marcaRepository.findAll(); }
    public Marca findById(Long id){ return marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca não encontrada")); }
    public Marca update(Long id, Marca dados){
        Marca m = findById(id);
        m.setNome(dados.getNome());
        return marcaRepository.save(m);
    }
    public void delete(Long id){ marcaRepository.deleteById(id); }
}
