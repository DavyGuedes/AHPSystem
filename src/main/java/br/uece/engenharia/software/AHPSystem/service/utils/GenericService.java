package br.uece.engenharia.software.AHPSystem.service.utils;

import java.util.List;
import java.util.Optional;

public interface GenericService<Entity, ID> {
    List<Entity> findAll();
    Optional<Entity> findById(ID id) ;
    void save(Entity entity);
    void delete(Entity entity);
}
