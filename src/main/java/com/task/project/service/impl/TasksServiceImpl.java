package com.task.project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.project.entity.Tasks;
import com.task.project.entity.Users;
import com.task.project.exception.APIException;
import com.task.project.exception.TasksNotFoundException;
import com.task.project.exception.UserNotFoundException;
import com.task.project.payload.TasksDto;
import com.task.project.repository.TasksRepository;
import com.task.project.repository.UsersRepository;
import com.task.project.service.TasksService;

@Service
public class TasksServiceImpl implements TasksService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TasksRepository tasksRepository;

	@Override
	public TasksDto saveTasks(Long userId, TasksDto tasksDto) {
		Users user = usersRepository.findById(userId).orElseThrow(
				()-> new UserNotFoundException(String.format("User Id %d not found", userId))
				);
		Tasks tasks = dtoToTasks(tasksDto);
		tasks.setUser(user);
		Tasks savedTasks = tasksRepository.save(tasks);
		return tasksToDto(savedTasks);
	}

	@Override
	public List<TasksDto> getAllTasks(Long userId) {
		usersRepository.findById(userId).orElseThrow(
				()-> new UserNotFoundException(String.format("User Id %d not found", userId))
				);
		List<Tasks> ListOfTasks = tasksRepository.findAllByUserId(userId);
		return ListOfTasks.stream().map(task->tasksToDto(task)).collect(Collectors.toList());
	}
	
	private Tasks dtoToTasks(TasksDto tasksDto) {
		Tasks tasks = new Tasks();
		BeanUtils.copyProperties(tasksDto, tasks);
		return tasks;
	}
	
	private TasksDto tasksToDto(Tasks tasks) {
		TasksDto tasksDto = new TasksDto();
		BeanUtils.copyProperties(tasks, tasksDto);
		return tasksDto;
	}

	@Override
	public TasksDto getTask(Long userId, Long tasksId) {
		Users user = usersRepository.findById(userId).orElseThrow(
				()-> new UserNotFoundException(String.format("User Id %d not found", userId))
				);
		Tasks task = tasksRepository.findById(tasksId).orElseThrow(
				()-> new TasksNotFoundException(String.format("Task Id %d not found", tasksId))
				);
		if(user.getId() != task.getUser().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", tasksId, userId));
		}
		return tasksToDto(task);
	}

	@Override
	public void deleteTask(Long userId, Long tasksId) {
		Users user = usersRepository.findById(userId).orElseThrow(
				()-> new UserNotFoundException(String.format("User Id %d not found", userId))
				);
		Tasks task = tasksRepository.findById(tasksId).orElseThrow(
				()-> new TasksNotFoundException(String.format("Task Id %d is not belongs to User Id %d", tasksId, userId))
				);
		if(user.getId() != task.getUser().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", tasksId, userId));
		}
		tasksRepository.deleteById(tasksId);
	}

}
