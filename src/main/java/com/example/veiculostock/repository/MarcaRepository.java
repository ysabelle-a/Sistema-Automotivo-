package com.example.veiculostock.repository;
import com.example.veiculostock.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNome(String nome);
}
