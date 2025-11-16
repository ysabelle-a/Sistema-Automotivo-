package com.example.veiculostock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "modelo")
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nome;

    @ManyToOne(optional=false)
    @JoinColumn(name="marca_id")
    private Marca marca;

    public Modelo() {}
    public Modelo(String nome, Marca marca){
        this.nome = nome;
        this.marca = marca;
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }
    public Marca getMarca(){ return marca; }
    public void setMarca(Marca marca){ this.marca = marca; }
}
