package com.todolist.controller;

import com.todolist.domain.Status;
import com.todolist.domain.Task;
import com.todolist.service.TaskService;
import com.todolist.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String listTasks(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {

        // Валидация параметров пагинации
        if (page < AppConstants.DEFAULT_PAGE_NUMBER) {
            page = AppConstants.DEFAULT_PAGE_NUMBER;
        }

        // Проверяем размер страницы на допустимые значения
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

        List<Task> tasks = taskService.getAllTasks(page, size);
        int totalPages = taskService.getTotalPages(size);

        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", size);
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("taskService", taskService);
        model.addAttribute("constants", AppConstants.class);

        return "task/list";
    }

    @PostMapping("/create")
    public String createTask(@Valid @ModelAttribute("task") Task task,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        if (result.hasErrors()) {
            // Возвращаем данные, необходимые для основной страницы
            List<Task> tasks = taskService.getAllTasks(page, size);
            int totalPages = taskService.getTotalPages(size);

            model.addAttribute("tasks", tasks);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pageSize", size);
            model.addAttribute("statuses", Status.values());
            model.addAttribute("taskService", taskService);
            model.addAttribute("constants", AppConstants.class);
            return "task/list";
        }

        taskService.createTask(task);
        redirectAttributes.addFlashAttribute("successMessage", AppConstants.TASK_CREATED_SUCCESS);
        return "redirect:/tasks?page=" + page + "&size=" + size;
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public String updateTaskInline(@PathVariable("id") Integer id,
                                   @RequestParam("description") String description,
                                   @RequestParam("status") String status) {
        try {
            if (description == null || description.trim().isEmpty()) {
                return AppConstants.DESCRIPTION_NOT_EMPTY;
            }

            if (description.length() > 100) {
                return AppConstants.DESCRIPTION_MAX_LENGTH;
            }

            Optional<Task> taskOptional = taskService.getTaskById(id);

            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                task.setDescription(description);

                try {
                    // Конвертируем строковый статус в enum
                    Status statusEnum = Status.valueOf(status);
                    task.setStatus(statusEnum);
                } catch (IllegalArgumentException e) {
                    return "Invalid status value";
                }

                taskService.updateTask(task);
                return AppConstants.TASK_UPDATED_SUCCESS;
            } else {
                return AppConstants.TASK_NOT_FOUND;
            }
        } catch (Exception e) {
            return AppConstants.ERROR_UPDATING_TASK + e.getMessage();
        }
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteTaskAjax(@PathVariable("id") Integer id) {
        try {
            Optional<Task> task = taskService.getTaskById(id);
            if (task.isPresent()) {
                taskService.deleteTask(id);
                return AppConstants.TASK_DELETED_SUCCESS;
            } else {
                return AppConstants.TASK_NOT_FOUND;
            }
        } catch (Exception e) {
            return AppConstants.ERROR_DELETING_TASK + e.getMessage();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id,
                             RedirectAttributes redirectAttributes,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Optional<Task> task = taskService.getTaskById(id);
            if (task.isPresent()) {
                taskService.deleteTask(id);
                redirectAttributes.addFlashAttribute("successMessage", AppConstants.TASK_DELETED_SUCCESS);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", AppConstants.TASK_NOT_FOUND);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", AppConstants.ERROR_DELETING_TASK + e.getMessage());
        }
        return "redirect:/tasks?page=" + page + "&size=" + size;
    }
}