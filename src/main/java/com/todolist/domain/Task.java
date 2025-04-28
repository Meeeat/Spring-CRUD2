package com.todolist.domain;

import com.todolist.util.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = AppConstants.DESCRIPTION_NOT_EMPTY)
    @Size(max = 100, message = AppConstants.DESCRIPTION_MAX_LENGTH)
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status = Status.IN_PROGRESS;
}