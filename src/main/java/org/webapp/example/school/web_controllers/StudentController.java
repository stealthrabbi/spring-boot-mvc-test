package org.webapp.example.school.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.webapp.example.school.data.repository.StudentRepository;
import org.webapp.example.school.domain.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// This marks it as a Controller, and also makes methods return JSON by default and eliminates the need for making
// return values with @ResponseBody
@RestController
@RequestMapping("students")
public class StudentController {

    // This will auto convert a string matching the specified date format in to a java.util.Date.
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Student.DATE_FORMAT);
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
    @GetMapping(value = "list")
    public List<Student> listStudents() {
        return mStudentRepository.getAllStudents();
    }


    // Use ResponseEntity<> here to return a non-OK code when student is not found.
    @GetMapping(value = "{name}")
    public ResponseEntity<?> getStudent(@PathVariable String name) {
        Student student = mStudentRepository.getStudent(name);

        if (null == student) {
            return ResponseEntity.badRequest().body("Could not find student: " + name);
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping(value = "add")
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
