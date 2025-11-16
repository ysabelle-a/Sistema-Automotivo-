package com.example.veiculostock.service;

import com.example.veiculostock.entity.Veiculo;
import com.example.veiculostock.repository.VeiculoRepository;
import com.example.veiculostock.specification.VeiculoSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository repo;
    public VeiculoService(VeiculoRepository repo){ this.repo = repo; }

    public Veiculo create(Veiculo v){ return repo.save(v); }

    public Page<Veiculo> findWithFilter(
            Optional<String> marca,
            Optional<String> modelo,
            Optional<BigDecimal> minPreco,
            Optional<BigDecimal> maxPreco,
            Optional<Integer> ano,
            Optional<String> status,
            int page, int size, Sort sort
    ){
        Specification<Veiculo> spec = VeiculoSpecification.filter(
                marca.orElse(null),
                modelo.orElse(null),
                minPreco.orElse(null),
                maxPreco.orElse(null),
                ano.orElse(null),
                status.orElse(null)
        );

        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(spec, pageable);
    }

    public Veiculo findById(Long id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Veículo não encontrado")); }

    public Veiculo update(Long id, Veiculo updated){
        Veiculo v = findById(id);
        v.setPreco(updated.getPreco());
        v.setQuilometragem(updated.getQuilometragem());
        v.setStatus(updated.getStatus());
        v.setCor(updated.getCor());
        v.setAno(updated.getAno());
        // if changing modelo, setModelo(updated.getModelo())
        return repo.save(v);
    }

    public void delete(Long id){ repo.deleteById(id); }
}
