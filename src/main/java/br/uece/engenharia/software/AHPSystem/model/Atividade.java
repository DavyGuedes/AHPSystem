package br.uece.engenharia.software.AHPSystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Atividade extends AbstractPersistable<Long> {
    private String nome;
    private String descricao;
    @ManyToMany(mappedBy = "atividades")
    private Set<Portifolio> portifolios = new HashSet<>();
}
