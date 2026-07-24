package com.example.CRUDApplication.service;

import com.example.CRUDApplication.exception.DuplicateUserException;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.mapper.MapRecordToStudent;
import com.example.CRUDApplication.mapper.MapStudentToStudentResponseDTO;
import com.example.CRUDApplication.dto.StudentCreateDTO;
import com.example.CRUDApplication.dto.StudentResponseDTO;
import com.example.CRUDApplication.dto.StudentUpdateDTO;
import com.example.CRUDApplication.entity.Student;
import com.example.CRUDApplication.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.jooq.Record;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService  {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }




    public StudentResponseDTO getStudentById(UUID id) { // Changed Long to Integer
        Record studentResponse = studentRepository.findByIdAndDeletedIsFalse(id);

        Student student = Optional.ofNullable(studentResponse)
                .map(MapRecordToStudent::recordToStudentMap)
                .orElseThrow(()->new ResourceNotFoundException("Student Details not Found"));

        return MapStudentToStudentResponseDTO.StudentToStudentDTOMap(student);

    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Record> studentResponse = studentRepository.findByDeletedIsFalse();
        if(studentResponse==null||studentResponse.isEmpty()){
            throw new ResourceNotFoundException("No Data Found");
        }
        return studentResponse.stream()
                .map(MapRecordToStudent::recordToStudentMap)
                .map(MapStudentToStudentResponseDTO::StudentToStudentDTOMap)
                .toList();
    }

    @Transactional
    public void updateStudentById(UUID id, StudentUpdateDTO studentreq) { // Changed Long to Integer
        Record existingRecord = studentRepository.findByIdAndDeletedIsFalse(id);
        Optional.ofNullable(existingRecord)
                .orElseThrow(()->new ResourceNotFoundException("Update Failed : Not student Found"));
        boolean isUpdated=studentRepository.update(id, studentreq);
        if(!isUpdated){
            throw new RuntimeException("Something went Wrong..");
        }
    }

    public void deleteById(UUID id) { // Changed Long to Integer
        boolean isDeleted= studentRepository.deleteById(id);
        if(!isDeleted){
            throw new ResourceNotFoundException("Delete Failed : No Such student Data Found");
        }
    }


}
