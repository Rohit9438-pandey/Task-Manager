package com.rohitproject.Task.Management.System.Controller;

import com.rohitproject.Task.Management.System.Entity.Task;
import com.rohitproject.Task.Management.System.Entity.User;
import com.rohitproject.Task.Management.System.Repository.TaskRepository;
import com.rohitproject.Task.Management.System.Repository.UserRepository;
import com.rohitproject.Task.Management.System.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/task")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<Task> getAll()
    {
      return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id)
    {
        return taskService.getTaskById(id);
    }

    @GetMapping
    public List<Task> getTasks(Authentication authentication)
    {
        String username = authentication.getName();
        return taskRepository.findByUserUsername(username);
    }


    @PostMapping
    public Task createTask(@RequestBody Task task, Authentication authentication) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        task.setUser(user);

        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id , @RequestBody Task task)
    {
        return taskService.updateTask(id ,task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, Authentication auth) {
        Task task = taskRepository.findById(id).orElseThrow();

        if (!task.getUser().getUsername().equals(auth.getName())) {
            throw new RuntimeException("Unauthorized");
        }

        taskRepository.delete(task);
    }
}
