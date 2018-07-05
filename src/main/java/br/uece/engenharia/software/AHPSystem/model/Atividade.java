package br.uece.engenharia.software.AHPSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Atividade extends BaseEntity {
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Portfolio portfolio;

    public Atividade() {
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Portfolio getPortfolio() {
        return this.portfolio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

}
