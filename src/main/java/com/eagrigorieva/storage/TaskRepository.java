package com.eagrigorieva.storage;

import com.eagrigorieva.model.Task;
import com.eagrigorieva.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(Users user);

    Optional<Task> findByIdAndUser(Long id, Users user);
}
