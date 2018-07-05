package br.uece.engenharia.software.AHPSystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Criterio extends BaseEntity {
    private String nome;
    private String descricao;
    @ManyToMany(mappedBy = "criterios")
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
