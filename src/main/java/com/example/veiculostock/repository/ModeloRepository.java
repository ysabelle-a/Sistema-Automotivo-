package com.example.veiculostock.repository;
import com.example.veiculostock.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByMarcaId(Long marcaId);
}
