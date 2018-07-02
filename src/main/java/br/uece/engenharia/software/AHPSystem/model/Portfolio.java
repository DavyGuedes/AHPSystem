package br.uece.engenharia.software.AHPSystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Data
@Entity
public class Portfolio extends BaseEntity {

    @NotBlank
    private String nome;
    @NotBlank
    private String meta;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Portfolio_Atividade",
            joinColumns = { @JoinColumn(name = "portfolio_id") },
            inverseJoinColumns = { @JoinColumn(name = "atividade_id") }
    )
    private Set<Atividade> atividades = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Portfolio_Criterio",
            joinColumns = { @JoinColumn(name = "portfolio_id") },
            inverseJoinColumns = { @JoinColumn(name = "criterio_id") }
    )
    private Set<Criterio> criterios = new HashSet<>();

}
