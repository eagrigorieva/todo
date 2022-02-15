package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "task_status")
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return String.format("[%s] %s", taskStatus == CREATED ? " " : "x", description);
    }
}
