package com.rohitproject.Task.Management.System.Service;

import com.rohitproject.Task.Management.System.Entity.Task;
import com.rohitproject.Task.Management.System.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask()
    {
        return taskRepository.findAll();
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task saveTask(Task task)
    {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id , Task task)
    {
        Task t = taskRepository.findById(id).orElseThrow();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
      return taskRepository.save(t);

    }

    public void deleteTask(Long id)
    {
        taskRepository.deleteById(id);
    }


}
