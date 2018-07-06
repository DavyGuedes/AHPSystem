package br.uece.engenharia.software.AHPSystem.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Criterio extends BaseEntity {
    private String nome;
    private String descricao;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Portfolio_Criterio",
        joinColumns = {
            @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "criterio_id",referencedColumnName = "id")
        }
    )
    private Set<Portfolio> portfolios = new HashSet<>();

    public Criterio() {
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Set<Portfolio> getPortfolios() {
        return this.portfolios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPortfolios(Set<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

}
