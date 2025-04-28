package com.todolist.service;

import com.todolist.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks(int page, int size);

    long getTotalTasksCount();

    int getTotalPages(int size);

    Optional<Task> getTaskById(Integer id);

    void createTask(Task task);

    void updateTask(Task task);

    void deleteTask(Integer id);
}