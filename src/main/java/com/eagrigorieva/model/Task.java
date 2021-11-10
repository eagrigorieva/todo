package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Log4j2
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
