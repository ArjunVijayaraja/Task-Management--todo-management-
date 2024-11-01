package com.example.todo_management.dtoEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodosDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
