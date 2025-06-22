package com.github.andre10dias.sbootexp_security.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "senha")
    private String senha;

}
