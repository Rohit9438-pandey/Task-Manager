package com.rohitproject.Task.Management.System.Repository;

import com.rohitproject.Task.Management.System.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task , Long> {

    List<Task> findByUserUsername(String username);
}
