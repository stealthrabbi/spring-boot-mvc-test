package org.webapp.example.school.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.webapp.example.school.data_repository.StudentRepository;
import org.webapp.example.school.domain_model.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark on 1/22/2017.
 */

@Controller
@RequestMapping("students")
public class StudentController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private final StudentRepository mStudentRepository;

    // The field mStudentRepository could be @Autowired, but it's unclear what the dependencies
    // are to the user of the class, and it becomes impossible to use the class without using spring. It would hide
    // dependencies and potentially violate Single repsonsibility principle as it is almost too easy to add more
    // autowired dependencies, and the list of depedencies could grow somewhat unchecked by simply adding
    // more and more autowired fields.
    @Autowired
    public StudentController(StudentRepository studentRepository) {
        mStudentRepository = studentRepository;
    }

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

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStudent(@ModelAttribute Student student) {
        if (null == student || StringUtils.isEmpty(student.getName())
                            || StringUtils.isEmpty(student.getBirthDate())
                            || StringUtils.isEmpty(student.getSocialSecurityNumber())) {
            return ResponseEntity.badRequest().body("no student given");
        }
        mStudentRepository.addStudent(student);
        return ResponseEntity.ok(student);
    }
}
