package com.example.springcrudstudent.controller;

import com.example.springcrudstudent.model.Student;
import com.example.springcrudstudent.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Create
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student saved = service.create(student);
        return ResponseEntity.created(URI.create("/api/students/" + saved.getId())).body(saved);
    }

    // Read all
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        return service.update(id, student)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = service.delete(id);
        if (removed) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    // (Optional) clear all - useful in demo/testing
    @DeleteMapping
    public ResponseEntity<Void> clearAll() {
        service.clearAll();
        return ResponseEntity.noContent().build();
    }
}
