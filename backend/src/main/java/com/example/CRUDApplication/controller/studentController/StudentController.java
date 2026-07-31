package com.example.CRUDApplication.controller.studentController;

import com.example.CRUDApplication.dto.studentDTO.StudentResponseDTO;
import com.example.CRUDApplication.dto.studentDTO.StudentUpdateDTO;
import com.example.CRUDApplication.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id){
        StudentResponseDTO responseByIdStudent = studentService.getStudentById(id);
        return ResponseEntity.ok(responseByIdStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent(){
        List<StudentResponseDTO> responseStudentsList = studentService.getAllStudents();
        return ResponseEntity.ok(responseStudentsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable UUID id,
                                                    @Valid @RequestBody StudentUpdateDTO reqStudent){
        studentService.updateStudentById(id, reqStudent);
        return ResponseEntity.ok("Data updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable UUID id){
        studentService.deleteById(id);
        return ResponseEntity.ok("Record Deleted");

    }
}
