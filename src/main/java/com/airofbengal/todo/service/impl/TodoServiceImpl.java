package com.airofbengal.todo.service.impl;

import com.airofbengal.todo.dto.TodoDto;
import com.airofbengal.todo.entity.Todo;
import com.airofbengal.todo.mapper.TodoMapper;
import com.airofbengal.todo.repository.TodoRepository;
import com.airofbengal.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = TodoMapper.mapToTodo(todoDto);
        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(savedTodo);
    }
}
