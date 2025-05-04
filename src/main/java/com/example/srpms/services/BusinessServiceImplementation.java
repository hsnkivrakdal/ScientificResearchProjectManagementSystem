package com.example.srpms.services;

import com.example.srpms.repository.ProjectActivityAreaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BusinessServiceImplementation<T, Integer> implements BusinessServices<T, Integer> {

    protected final JpaRepository<T, Integer> repository;
    protected BusinessServiceImplementation(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
