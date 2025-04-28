package com.todolist.service;

import com.todolist.dao.TaskDAO;
import com.todolist.domain.Task;
import com.todolist.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(int page, int size) {
        // Проверка параметров пагинации и их корректировка, если необходимо
        if (page < AppConstants.DEFAULT_PAGE_NUMBER) {
            page = AppConstants.DEFAULT_PAGE_NUMBER;
        }

        // Проверяем, содержится ли размер страницы в списке доступных размеров
        boolean validSize = false;
        for (int availableSize : AppConstants.AVAILABLE_PAGE_SIZES) {
            if (size == availableSize) {
                validSize = true;
                break;
            }
        }

        if (!validSize) {
            size = AppConstants.DEFAULT_PAGE_SIZE;
        }

        return taskDAO.findAll(page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalTasksCount() {
        return taskDAO.count();
    }

    @Override
    @Transactional(readOnly = true)
    public int getTotalPages(int size) {
        // Проверка размера страницы и его корректировка, если необходимо
        boolean validSize = false;
        for (int availableSize : AppConstants.AVAILABLE_PAGE_SIZES) {
            if (size == availableSize) {
                validSize = true;
                break;
            }
        }

        if (!validSize) {
            size = AppConstants.DEFAULT_PAGE_SIZE;
        }

        long count = getTotalTasksCount();
        return (int) Math.ceil((double) count / size);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Integer id) {
        return taskDAO.findById(id);
    }

    @Override
    @Transactional
    public void createTask(Task task) {
        taskDAO.save(task);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        taskDAO.update(task);
    }

    @Override
    @Transactional
    public void deleteTask(Integer id) {
        taskDAO.delete(id);
    }
}