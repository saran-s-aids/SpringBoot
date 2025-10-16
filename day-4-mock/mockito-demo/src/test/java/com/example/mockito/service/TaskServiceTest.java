package com.example.mockito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.mockito.model.Task;
import com.example.mockito.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        // MockitoExtension takes care of this automatically
    }

    @Test
    void testAddTask() {
        Task task = new Task(1, "LearnMockito");
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.addTask(task);

        assertEquals("LearnMockito", result.getName());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testAddTaskWithEmptyName() {
        Task task = new Task(1, "");
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(task));
        verify(taskRepository, never()).save(any());
    }

    @Test
    void testGetAllTask() {
        List<Task> tasks = Arrays.asList(new Task(1, "A"), new Task(2, "B"));
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTask();

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }
}
