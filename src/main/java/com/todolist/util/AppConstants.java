package com.todolist.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Application constants
 * Содержит все константные строки приложения для централизованного управления текстами
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConstants {
    // Success messages
    public static final String TASK_CREATED_SUCCESS = "Task created successfully!";
    public static final String TASK_UPDATED_SUCCESS = "Task updated successfully!";
    public static final String TASK_DELETED_SUCCESS = "Task deleted successfully!";

    // Error messages
    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String ERROR_UPDATING_TASK = "Error updating task: ";
    public static final String ERROR_DELETING_TASK = "Error deleting task: ";
    public static final String ERROR_PREFIX = "Error: ";
    public static final String DESCRIPTION_EMPTY_ERROR = "Description cannot be empty!";

    // Validation messages
    public static final String DESCRIPTION_NOT_EMPTY = "Description cannot be empty";
    public static final String DESCRIPTION_MAX_LENGTH = "Description must be less than 100 characters";

    // View text
    public static final String TASK_LIST_TITLE = "Task List";
    public static final String NO_TASKS_FOUND = "No tasks found";
    public static final String CREATE_NEW_TASK = "Create New Task";
    public static final String TASK_TOTAL_COUNT = "Total: %d / %d";
    public static final String PAGE_SIZE_TEXT = "Page size:";
    public static final String ITEMS_PER_PAGE_TEXT = "items per page";

    // Table headers
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_ACTIONS = "Actions";

    // Button labels
    public static final String BUTTON_ADD = "Add Task";
    public static final String BUTTON_EDIT = "Edit";
    public static final String BUTTON_DELETE = "Delete";
    public static final String BUTTON_SAVE = "Save";
    public static final String BUTTON_CANCEL = "Cancel";

    // Pagination defaults
    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int[] AVAILABLE_PAGE_SIZES = {5, 10, 20, 50};

    // UI constants
    public static final int ALERT_AUTO_CLOSE_DELAY = 5000; // миллисекунды

    // Table column widths
    public static final String COLUMN_ID_WIDTH = "8%";
    public static final String COLUMN_DESCRIPTION_WIDTH = "50%";
    public static final String COLUMN_STATUS_WIDTH = "17%";
    public static final String COLUMN_ACTIONS_WIDTH = "25%";

    // CSS classes
    public static final String IN_PROGRESS_CLASS = "task-status-0";
    public static final String DONE_CLASS = "task-status-1";
    public static final String PAUSED_CLASS = "task-status-2";
    public static final String IN_PROGRESS_BADGE = "bg-warning";
    public static final String DONE_BADGE = "bg-success";
    public static final String PAUSED_BADGE = "bg-danger";
}