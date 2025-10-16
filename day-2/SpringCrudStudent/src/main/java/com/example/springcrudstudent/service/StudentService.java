package com.example.springcrudstudent.service;

import com.example.springcrudstudent.model.Student;
import com.example.springcrudstudent.repository.InMemoryStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final InMemoryStudentRepository repository;

    public StudentService(InMemoryStudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Student> update(Long id, Student student) {
        return repository.update(id, student);
    }

    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    // helper for demo/testing
    public void clearAll() {
        repository.clear();
    }
}
