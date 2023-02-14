package com.task.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.project.entity.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

	List<Tasks> findAllByUserId(Long userId);

	Tasks findByUserIdAndId(Long userId, Long tasksId);

}
