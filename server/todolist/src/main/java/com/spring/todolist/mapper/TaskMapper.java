package com.spring.todolist.mapper;

import com.spring.todolist.model.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {
    Task taskToTask(Task source, Task destination);
}
