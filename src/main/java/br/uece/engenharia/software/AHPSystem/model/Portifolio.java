package br.uece.engenharia.software.AHPSystem.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Data
@Entity
public class Portifolio extends AbstractPersistable<Long> {
    @NotBlank
    @NotBlank
    private String nome;
    @NotBlank
    @NotBlank
    private String meta;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Portifolio_Atividade",
            joinColumns = { @JoinColumn(name = "portifolio_id") },
            inverseJoinColumns = { @JoinColumn(name = "atividade_id") }
    )
    private Set<Atividade> atividades = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Portifolio_Criterio",
            joinColumns = { @JoinColumn(name = "portifolio_id") },
            inverseJoinColumns = { @JoinColumn(name = "criterio_id") }
    )
    private Set<Criterio> criterios = new HashSet<>();

}
