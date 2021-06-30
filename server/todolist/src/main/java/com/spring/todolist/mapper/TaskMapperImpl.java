package com.spring.todolist.mapper;

import com.spring.todolist.model.Task;

public class TaskMapperImpl implements TaskMapper{
    @Override
    public Task taskToTask(Task source, Task destination){
        destination.setInput(source.getInput());
        destination.setTitle(source.getTitle());
        destination.setStatus(source.getStatus());
        destination.setCreationDate(source.getCreationDate());
        destination.setLastModifiedDate(source.getLastModifiedDate());
        destination.setCategories(source.getCategories());
        return destination;
    }
}
