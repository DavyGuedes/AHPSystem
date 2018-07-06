package br.uece.engenharia.software.AHPSystem.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<Entity, ID> implements GenericService<Entity, ID> {
    @Autowired
    protected JpaRepository<Entity, ID> repository;

    @Override
    public List<Entity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void save(Entity entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Entity entity) {
        repository.delete(entity);
    }
}
