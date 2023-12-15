package com.javaspringproject.springboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static java.util.Arrays.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email alrealy exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            System.out.println("NOT DELETED");
            throw new IllegalStateException(
              "Student id: " + studentId + " doesn't exists."
            );
        }
        studentRepository.deleteById(studentId);
    }

//    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student id: " + studentId + " doesn't exists."
                ));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }
        studentRepository.flush();
//        save() attaches the entity to the session and at the end of the transaction as
//        long as there were no exceptions it will all be persisted to the database.
//
//        Now if you get the object from the DB (e.g. ObjectEntity objectEntity = objectRepository.find();)
//        then that object is already attached and you don't need to call the save() method.
//
//        If the object, however, is detached (e.g. ObjectEntity objectEntity = new ObjectEntity();)
//        then you must use the save() method in order to attach it to the session so that changes made to it
//        are persisted to the DB.
    }
}
