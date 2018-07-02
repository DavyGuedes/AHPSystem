package br.uece.engenharia.software.AHPSystem.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Criterio extends BaseEntity {
    private String nome;
    private String descricao;
    @ManyToMany(mappedBy = "criterios")
    private Set<Portfolio> portfolios = new HashSet<>();
}
