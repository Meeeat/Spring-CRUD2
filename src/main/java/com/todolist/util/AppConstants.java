package com.todolist.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
    public static final String INVALID_STATUS_VALUE = "Invalid status value";

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
    public static final String DELETE_CONFIRMATION = "Are you sure you want to delete this task?";

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
    public static final String DEFAULT_PAGE_SIZE_STR = "5";
    public static final String DEFAULT_PAGE_NUMBER_STR = "1";
    public static final String DEFAULT_PAGE_SIZE_LARGE_STR = "10";

    // UI constants
    public static final int ALERT_AUTO_CLOSE_DELAY = 5000;
    public static final String TASKS_URL = "/tasks";
    public static final String REDIRECT_TASKS = "redirect:/tasks";
    public static final String REDIRECT_FORMAT = "redirect:/tasks?page=%d&size=%d";

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

    // Request parameters
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_SIZE = "size";
    public static final String PARAM_DESCRIPTION = "description";
    public static final String PARAM_STATUS = "status";

    // Model attributes
    public static final String ATTR_TASKS = "tasks";
    public static final String ATTR_CURRENT_PAGE = "currentPage";
    public static final String ATTR_TOTAL_PAGES = "totalPages";
    public static final String ATTR_PAGE_SIZE = "pageSize";
    public static final String ATTR_TASK = "task";
    public static final String ATTR_STATUSES = "statuses";
    public static final String ATTR_TASK_SERVICE = "taskService";
    public static final String ATTR_CONSTANTS = "constants";
    public static final String ATTR_SUCCESS_MESSAGE = "successMessage";
    public static final String ATTR_ERROR_MESSAGE = "errorMessage";

    // Path variables and endpoints
    public static final String PATH_ID = "id";
    public static final String ENDPOINT_TASKS = "/tasks";
    public static final String ENDPOINT_CREATE = "/create";
    public static final String ENDPOINT_UPDATE_ID = "/update/{id}";
    public static final String ENDPOINT_DELETE_ID = "/delete/{id}";

    // UI Element IDs
    public static final String ID_PAGE_SIZE_SELECTOR = "pageSizeSelector";

    // Database configuration
    //public static final String DB_URL = "jdbc:mysql://localhost:3306/todo";
    public static final String DB_URL = "jdbc:p6spy:mysql://localhost:3306/todo";
    //public static final String DB_URL = "jdbc:p6spy:mysql://db:3306/todo";
    //public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_DRIVER = "com.p6spy.engine.spy.P6SpyDriver";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";
    public static final int DB_MAX_POOL_SIZE = 10;
    public static final int DB_MIN_IDLE = 5;
    public static final int DB_IDLE_TIMEOUT = 30000;
    public static final String DB_POOL_NAME = "SpringHikariCP";

    // Hibernate properties
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_DIALECT_VALUE = "org.hibernate.dialect.MySQLDialect";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String HIBERNATE_SHOW_SQL_VALUE = "true";
    public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String HIBERNATE_FORMAT_SQL_VALUE = "true";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_HBM2DDL_AUTO_VALUE = "validate";

    // View names
    public static final String VIEW_TASK_LIST = "task/list";
    public static final String VIEW_REDIRECT_HOME = "redirect:/";

    // Package names
    public static final String PACKAGE_DAO_SERVICE = "com.todolist.dao, com.todolist.service";
    public static final String PACKAGE_CONTROLLER = "com.todolist.controller";
    public static final String PACKAGE_DOMAIN = "com.todolist.domain";

    // Entity fields
    public static final int DESCRIPTION_MAX_LENGTH_VALUE = 100;
}