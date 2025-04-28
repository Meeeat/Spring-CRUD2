package com.todolist.dao;

import com.todolist.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDAO {

    List<Task> findAll(int page, int size);

    long count();

    Optional<Task> findById(Integer id);

    void save(Task task);

    void update(Task task);

    void delete(Integer id);
}