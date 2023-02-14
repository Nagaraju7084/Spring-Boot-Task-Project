package com.task.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.project.payload.TasksDto;
import com.task.project.service.TasksService;

@RestController
@RequestMapping("/api")
public class TasksController {

	@Autowired
	private TasksService tasksService;
	
	//store/save the task
	@PostMapping("/{userId}/tasks")
	public ResponseEntity<TasksDto> saveTasks(@PathVariable(name = "userId") long userId,
			@RequestBody TasksDto tasksDto){
		return new ResponseEntity<TasksDto>(tasksService.saveTasks(userId, tasksDto),HttpStatus.CREATED);
	}
	
	//get all tasks
	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TasksDto>> getAllTasksByUserId(
			@PathVariable(name = "userId") Long userId){
		return new ResponseEntity<>(tasksService.getAllTasks(userId),HttpStatus.OK);
	}
	
	//get single task
	@GetMapping("/{userId}/tasks/{tasksId}")
	public ResponseEntity<TasksDto> getTaskByUserId(
			@PathVariable Long userId,
			@PathVariable Long tasksId
			) {
		return new ResponseEntity<TasksDto>(tasksService.getTask(userId, tasksId), HttpStatus.OK);
	}
	
	//delete single task
	@DeleteMapping("/{userId}/tasks/{tasksId}")
	public ResponseEntity<String> deleteByTaskId(
			@PathVariable Long userId,
			@PathVariable Long tasksId
			){
		tasksService.deleteTask(userId, tasksId);
		return new ResponseEntity<String>("task deleted successfully!",HttpStatus.OK);
	}
}
