package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Todo} entity.
 */
public class TodoDTO implements Serializable {
    
    private Long id;

    private String description;

    private String details;

    private Boolean done;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TodoDTO todoDTO = (TodoDTO) o;
        if (todoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), todoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TodoDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", details='" + getDetails() + "'" +
            ", done='" + isDone() + "'" +
            "}";
    }
}
