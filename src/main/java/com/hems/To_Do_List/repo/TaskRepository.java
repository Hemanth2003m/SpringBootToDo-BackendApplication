package com.hems.To_Do_List.repo;

import com.hems.To_Do_List.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUsername(String username);

    Optional<Task> findByIdAndUsername(Long id, String username);
}
