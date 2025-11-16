package com.example.veiculostock.specification;

import com.example.veiculostock.entity.Veiculo;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class VeiculoSpecification {
    public static Specification<Veiculo> filter(
            String marca,
            String modelo,
            BigDecimal minPreco,
            BigDecimal maxPreco,
            Integer ano,
            String status
    ) {
        return (Root<Veiculo> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate p = cb.conjunction();

            if (marca != null && !marca.isBlank()) {
                Join<Object, Object> modeloJoin = root.join("modelo");
                Join<Object, Object> marcaJoin = modeloJoin.join("marca");
                p = cb.and(p, cb.equal(cb.lower(marcaJoin.get("nome")), marca.toLowerCase()));
            }

            if (modelo != null && !modelo.isBlank()) {
                Join<Object, Object> modeloJoin = root.join("modelo");
                p = cb.and(p, cb.equal(cb.lower(modeloJoin.get("nome")), modelo.toLowerCase()));
            }

            if (minPreco != null) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("preco"), minPreco));
            }
            if (maxPreco != null) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("preco"), maxPreco));
            }

            if (ano != null) {
                p = cb.and(p, cb.equal(root.get("ano"), ano));
            }

            if (status != null && !status.isBlank()) {
                p = cb.and(p, cb.equal(cb.lower(root.get("status")), status.toLowerCase()));
            }

            return p;
        };
    }
}
