package com.eagrigorieva.model;

import com.eagrigorieva.enumeration.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.eagrigorieva.enumeration.TaskStatus.CREATED;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=2, max=100)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "task_status")
    private TaskStatus taskStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return String.format("[%s] %s", taskStatus == CREATED ? " " : "x", description);
    }
}
