package com.mytodoapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;

    public Task( int id, String title, String description,boolean isCompleted) {
        this.description = description;
        this.id = id;
        this.isCompleted = isCompleted;
        this.title = title;
    }
}
