package br.com.puctech.battle_arena.model;

import jakarta.persistence.*;

@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int dano;

    @ManyToOne
    private Player dono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public Player getDono() {
        return dono;
    }

    public void setDono(Player dono) {
        this.dono = dono;
    }
}
