package com.example.veiculostock.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="modelo_id")
    private Modelo modelo;

    @Column(nullable=false)
    private Integer ano;

    @Column(nullable=false)
    private String cor;

    @Column(nullable=false, scale=2, precision=12)
    private BigDecimal preco;

    @Column(nullable=false)
    private Long quilometragem;

    @Column(nullable=false)
    private String status; // ex: DISPONIVEL, VENDIDO, DESCONTINUADO

    public Veiculo() {}

    public Veiculo(Modelo modelo, Integer ano, String cor, BigDecimal preco, Long quilometragem, String status){
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.quilometragem = quilometragem;
        this.status = status;
    }

    // getters e setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Modelo getModelo(){ return modelo; }
    public void setModelo(Modelo modelo){ this.modelo = modelo; }
    public Integer getAno(){ return ano; }
    public void setAno(Integer ano){ this.ano = ano; }
    public String getCor(){ return cor; }
    public void setCor(String cor){ this.cor = cor; }
    public BigDecimal getPreco(){ return preco; }
    public void setPreco(BigDecimal preco){ this.preco = preco; }
    public Long getQuilometragem(){ return quilometragem; }
    public void setQuilometragem(Long quilometragem){ this.quilometragem = quilometragem; }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
}
