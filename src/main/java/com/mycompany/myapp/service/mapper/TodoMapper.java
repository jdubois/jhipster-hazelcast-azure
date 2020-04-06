package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TodoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Todo} and its DTO {@link TodoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TodoMapper extends EntityMapper<TodoDTO, Todo> {



    default Todo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Todo todo = new Todo();
        todo.setId(id);
        return todo;
    }
}
