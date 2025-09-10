package com.hems.To_Do_List.service;


import com.hems.To_Do_List.model.Task;
import com.hems.To_Do_List.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task, String username) {
        task.setUsername(username); // bind task to Vinay
        return taskRepository.save(task);
    }

    public List<Task> getTasksForUser(String username) {
        return taskRepository.findByUsername(username); // fetch only Vinay’s tasks
    }

    public boolean deleteTask(Long taskId, String username) {
        Optional<Task> taskOpt = taskRepository.findByIdAndUsername(taskId, username);

        if(taskOpt.isPresent()) {
            taskRepository.delete(taskOpt.get());
            return true;
        } else {
            return false; // task not found or does not belong to user
        }
    }
}
