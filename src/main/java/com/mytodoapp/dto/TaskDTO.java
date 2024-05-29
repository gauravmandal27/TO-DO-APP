package com.mytodoapp.dto;

import java.time.LocalDateTime;

public record TaskDTO(int id,String title,String description,boolean isCompleted){
}

