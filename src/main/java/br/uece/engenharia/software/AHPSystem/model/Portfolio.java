package br.uece.engenharia.software.AHPSystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Portfolio extends BaseEntity {

    @NotBlank
    private String nome;
    @NotBlank
    private String meta;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Set<Atividade> atividades = new HashSet<>();

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
    private Set<Criterio> criterios = new HashSet<>();

    public Portfolio() {
    }

    public @NotBlank String getNome() {
        return this.nome;
    }

    public @NotBlank String getMeta() {
        return this.meta;
    }

    public Set<Atividade> getAtividades() {
        return this.atividades;
    }

    public Set<Criterio> getCriterios() {
        return this.criterios;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public void setMeta(@NotBlank String meta) {
        this.meta = meta;
    }

    public void setAtividades(Set<Atividade> atividades) {
        this.atividades = atividades;
    }

    public void setCriterios(Set<Criterio> criterios) {
        this.criterios = criterios;
    }

}
