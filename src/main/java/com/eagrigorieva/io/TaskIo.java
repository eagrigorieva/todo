package com.eagrigorieva.io;

import lombok.Data;

@Data
public class TaskIo {
    private Long id;
    private String description;
    private boolean completed;
}
