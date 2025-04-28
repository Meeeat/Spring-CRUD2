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
@RequestMapping(AppConstants.ENDPOINT_TASKS)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String listTasks(
            @RequestParam(value = AppConstants.PARAM_PAGE, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER_STR) int page,
            @RequestParam(value = AppConstants.PARAM_SIZE, defaultValue = AppConstants.DEFAULT_PAGE_SIZE_STR) int size,
            Model model) {

        List<Task> tasks = taskService.getAllTasks(page, size);
        int totalPages = taskService.getTotalPages(size);
        populateModel(model, tasks, page, size, totalPages);

        return AppConstants.VIEW_TASK_LIST;
    }

    @PostMapping(AppConstants.ENDPOINT_CREATE)
    public String createTask(@Valid @ModelAttribute(AppConstants.ATTR_TASK) Task task,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam(value = AppConstants.PARAM_PAGE, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER_STR) int page,
                             @RequestParam(value = AppConstants.PARAM_SIZE, defaultValue = AppConstants.DEFAULT_PAGE_SIZE_LARGE_STR) int size) {
        if (result.hasErrors()) {

            List<Task> tasks = taskService.getAllTasks(page, size);
            int totalPages = taskService.getTotalPages(size);

            populateModel(model, tasks, page, size, totalPages);

            return AppConstants.VIEW_TASK_LIST;
        }

        taskService.createTask(task);
        redirectAttributes.addFlashAttribute(AppConstants.ATTR_SUCCESS_MESSAGE, AppConstants.TASK_CREATED_SUCCESS);
        return String.format(AppConstants.REDIRECT_FORMAT, page, size);
    }

    @PostMapping(AppConstants.ENDPOINT_UPDATE_ID)
    @ResponseBody
    public String updateTaskInline(@PathVariable(AppConstants.PATH_ID) Integer id,
                                   @RequestParam(AppConstants.PARAM_DESCRIPTION) String description,
                                   @RequestParam(AppConstants.PARAM_STATUS) String status) {
        try {
            if (description == null || description.trim().isEmpty()) {
                return AppConstants.DESCRIPTION_NOT_EMPTY;
            }

            if (description.length() > AppConstants.DESCRIPTION_MAX_LENGTH_VALUE) {
                return AppConstants.DESCRIPTION_MAX_LENGTH;
            }

            Optional<Task> taskOptional = taskService.getTaskById(id);

            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                task.setDescription(description);

                try {
                    Status statusEnum = Status.valueOf(status);
                    task.setStatus(statusEnum);
                } catch (IllegalArgumentException e) {
                    return AppConstants.INVALID_STATUS_VALUE;
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

    @PostMapping(AppConstants.ENDPOINT_DELETE_ID)
    @ResponseBody
    public String deleteTaskAjax(@PathVariable(AppConstants.PATH_ID) Integer id) {
        try {
            return deleteTaskInternal(id) ? AppConstants.TASK_DELETED_SUCCESS : AppConstants.TASK_NOT_FOUND;
        } catch (Exception e) {
            return AppConstants.ERROR_DELETING_TASK + e.getMessage();
        }
    }

    @GetMapping(AppConstants.ENDPOINT_DELETE_ID)
    public String deleteTask(@PathVariable(AppConstants.PATH_ID) Integer id,
                             RedirectAttributes redirectAttributes,
                             @RequestParam(value = AppConstants.PARAM_PAGE, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER_STR) int page,
                             @RequestParam(value = AppConstants.PARAM_SIZE, defaultValue = AppConstants.DEFAULT_PAGE_SIZE_LARGE_STR) int size) {
        try {
            boolean deleted = deleteTaskInternal(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute(AppConstants.ATTR_SUCCESS_MESSAGE, AppConstants.TASK_DELETED_SUCCESS);
            } else {
                redirectAttributes.addFlashAttribute(AppConstants.ATTR_ERROR_MESSAGE, AppConstants.TASK_NOT_FOUND);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(AppConstants.ATTR_ERROR_MESSAGE, AppConstants.ERROR_DELETING_TASK + e.getMessage());
        }
        return String.format(AppConstants.REDIRECT_FORMAT, page, size);
    }


    private void populateModel(Model model, List<Task> tasks, int page, int size, int totalPages) {
        model.addAttribute(AppConstants.ATTR_TASKS, tasks);
        model.addAttribute(AppConstants.ATTR_CURRENT_PAGE, page);
        model.addAttribute(AppConstants.ATTR_TOTAL_PAGES, totalPages);
        model.addAttribute(AppConstants.ATTR_PAGE_SIZE, size);
        model.addAttribute(AppConstants.ATTR_TASK, new Task());
        model.addAttribute(AppConstants.ATTR_STATUSES, Status.values());
        model.addAttribute(AppConstants.ATTR_TASK_SERVICE, taskService);
        model.addAttribute(AppConstants.ATTR_CONSTANTS, AppConstants.class);
    }


    private boolean deleteTaskInternal(Integer id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            taskService.deleteTask(id);
            return true;
        }
        return false;
    }
}