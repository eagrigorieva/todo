package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Getter
@Setter
@AllArgsConstructor
public class Task {

    private String id;
    private String description;
    private TaskStatus taskStatus;

    @Override
    public String toString() {
        return String.format("[%s] %s", taskStatus == CREATED ? " " : "x", description);
    }
}
