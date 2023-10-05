package com.airofbengal.todo.service.impl;

import com.airofbengal.todo.dto.TodoDto;
import com.airofbengal.todo.entity.Todo;
import com.airofbengal.todo.exception.ResourceNotFoundException;
import com.airofbengal.todo.mapper.TodoMapper;
import com.airofbengal.todo.repository.TodoRepository;
import com.airofbengal.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+ id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getTodos() {
        List<TodoDto> todoDtos = todoRepository.findAll().stream().map((todo -> modelMapper.map(todo, TodoDto.class)))
                .collect(Collectors.toList());
        return todoDtos;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = modelMapper.map(getTodo(id), Todo.class);
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = modelMapper.map(getTodo(id), Todo.class);
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = modelMapper.map(getTodo(id), Todo.class);
        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = modelMapper.map(getTodo(id), Todo.class);
        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
