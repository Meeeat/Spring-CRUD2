package com.todolist.dao;

import com.todolist.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> findAll(int page, int size) {
        String s = "SELECT t FROM Task t ORDER BY t.id";
        TypedQuery<Task> query = entityManager.createQuery(s, Task.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public long count() {
        String s = "SELECT COUNT(t) FROM Task t";
        return entityManager.createQuery(s, Long.class).getSingleResult();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        Task task = entityManager.find(Task.class, id);
        return Optional.ofNullable(task);
    }

    @Override
    @Transactional
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    @Transactional
    public void update(Task task) {
        entityManager.merge(task);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null) {
            entityManager.remove(task);
        }
    }
}