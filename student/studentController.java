package com.example.student.controller;
import com.example.student.entity.student;
import com.example.student.repository.studentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class studentController {
@Autowired
    private studentRepository StudentRepository;
// CREATE
    @PostMapping
    public student createStudent(@RequestBody student student) {
        return StudentRepository.save(student);
    }
// READ All
    @GetMapping
    public List<student> getAllStudents() {
        return StudentRepository.findAll();
    }
// READ One
    @GetMapping("/{id}")
    public student getStudentById(@PathVariable Long id) {
        return StudentRepository.findById(id).orElse(null);
    }
// UPDATE
    @PutMapping("/{id}")
    public student updateStudent(@PathVariable Long id, @RequestBody student updatedStudent) {
        student student = StudentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            return StudentRepository.save(student);
        }
        return null;}
    

 @DeleteMapping("/{id}")
    public String DeleteStudent(@PathVariable Long id){
        StudentRepository.deleteById(id);
        
        return "student deleted successfully" ;
 
}
}
