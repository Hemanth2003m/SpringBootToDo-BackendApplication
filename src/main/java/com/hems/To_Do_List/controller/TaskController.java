package com.hems.To_Do_List.controller;

import com.hems.To_Do_List.model.Task;
import com.hems.To_Do_List.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Add a task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        // Get logged-in username from JWT
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Associate task with the logged-in user
        Task savedTask = taskService.addTask(task, username);
        return ResponseEntity.ok(savedTask);
    }

    // Get all tasks for logged-in user
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(taskService.getTasksForUser(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        // get logged-in user's username from JWT
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean deleted = taskService.deleteTask(id, username);
        if(deleted) {
            return ResponseEntity.ok("Task deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Task not found or you are not allowed to delete it");
        }
    }
}
