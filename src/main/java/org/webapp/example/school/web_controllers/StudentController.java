package org.webapp.example.school.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webapp.example.school.data_repository.StudentRepository;
import org.webapp.example.school.domain_model.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark on 1/22/2017.
 */

@Controller
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentRepository mStudentRepository;

    // ResponseBody is sufficent, because we'll always return a 200-OK response
    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Student> listStudents() {
        return mStudentRepository.getAllStudents();
    }


    // Use ResponseEntity<> here to return a non-OK code when student is not found.
    @RequestMapping(value = "{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudent(@PathVariable String name) {
        Student student = mStudentRepository.getStudent(name);

        if (null == student) {
            return ResponseEntity.badRequest().body("Could not find student: " + name);
        }
        return ResponseEntity.ok(student);
    }

    @RequestMapping(value = "name-find-2/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Student getStudent2(@PathVariable String name) {
        Student student = mStudentRepository.getStudent(name);

        if (null == student) {
        }
        return student;
    }
}
