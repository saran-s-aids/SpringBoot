package com.example.springcrudstudent.repository;

import com.example.springcrudstudent.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryStudentRepository {
    private final Map<Long, Student> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    // Create
    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idGenerator.incrementAndGet());
        }
        store.put(student.getId(), student);
        return student;
    }

    // Read all
    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    // Read by id
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // Delete
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    // Update (returns Optional)
    public Optional<Student> update(Long id, Student updated) {
        if (!store.containsKey(id)) return Optional.empty();
        Student existing = store.get(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());
        store.put(id, existing);
        return Optional.of(existing);
    }

    // For demo: clear all
    public void clear() {
        store.clear();
        idGenerator.set(0);
    }
}
