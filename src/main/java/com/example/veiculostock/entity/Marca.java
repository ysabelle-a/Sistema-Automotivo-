package com.example.veiculostock.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String nome;

    // constructors, getters, setters
    public Marca() {}
    public Marca(String nome){ this.nome = nome; }
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }
}
