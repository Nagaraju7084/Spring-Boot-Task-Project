package com.task.project.service;

import java.util.List;

import com.task.project.payload.TasksDto;

public interface TasksService {
	public TasksDto saveTasks(Long userId, TasksDto tasksDto);
	public List<TasksDto> getAllTasks(Long userId);
	public TasksDto getTask(Long userId, Long tasksId);
	public void deleteTask(Long userId, Long tasksId);
}
