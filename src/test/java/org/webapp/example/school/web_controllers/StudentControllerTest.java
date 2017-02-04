package org.webapp.example.school.web_controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.webapp.example.school.data_repository.StudentRepository;
import org.webapp.example.school.domain_model.Student;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test class for {@link StudentController}.
 */
public class StudentControllerTest extends AbstractControllerTest {

    // Need to use @Mock, rather than @MockBean to auto create a mock and inject it in to a Bean in to StudentController
    @MockBean
    private StudentRepository mStudentRepository;

    @InjectMocks
    private StudentController mStudentController;

    @Captor
    private ArgumentCaptor<Student> studentCaptor;

    private List<Student> mStudentList;


    @Before
    public void setUp() {
        // Prior to JUnit 4.5 Runner, you'd have to call MockitoAnnotations.initMocks(this) to initialize @Mocks and wire them in.
        // MockitoAnnotations.initMocks(this);

        // mock the student repository to provide a list of 3 students.
        mStudentList = new ArrayList<>();
        mStudentList.add(new Student("Egon Spengler", new Date(), "111-22-3333"));
        mStudentList.add(new Student("Peter Venkman", new Date(), "111-22-3334"));
        mStudentList.add(new Student("Raymond Stantz", new Date(), "111-22-3336"));
        mStudentList.add(new Student("Winston Zeddemore", new Date(), "111-22-3337"));

        when(mStudentRepository.getAllStudents()).thenReturn(mStudentList);
        when(mStudentRepository.getStudent("Peter Venkman")).thenReturn(mStudentList.get(1));
    }


    @Test
    public void listStudents() throws Exception {
        MvcResult result =
            mockMvc.perform(get("/students/list"))
            .andDo(print())
            .andExpect(jsonPath("$", hasSize(mStudentList.size())))
            .andExpect(jsonPath("$.[*].name", hasItems("Peter Venkman", "Egon Spengler", "Raymond Stantz", "Winston Zeddemore")))

            // doesn't work
//            .andExpect(jsonPath("$.[*]", hasItems(mStudentList.toArray())))
            // doesn't work
//            .andExpect(jsonPath("$.[*]", hasItems(mStudentList.get(0))))

            .andExpect(status().isOk())
                .andReturn();


        // alternate way of verifying, convert JSON to list of objects, and verify.
        String content = result.getResponse().getContentAsString();
        List<Student> students = mapFromJsonToList(content, Student.class);
        for (int i = 0; i < students.size(); i++) {
            Student student1 = students.get(i);
            assertEquals(mStudentList.get(i).getName(), student1.getName());
        }
    }

    @Test
    public void getStudentByName_ValidName() throws Exception {
        mockMvc.perform(get("/students/{name}", "Peter Venkman"))
            .andDo(print())
            .andExpect(jsonPath("$.name", is("Peter Venkman")))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void getStudentByName_InValidName() throws Exception {
        mockMvc.perform(get("/students/{name}", "Random Name"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void addStudent() throws Exception {
        mockMvc.perform(post("/students/add")
            .param("name", "Urist McTester")
            .param("socialSecurityNumber", "12345")
            .param("birthDate", "2010-04-05"))
            .andDo(print())
            .andExpect(status().isOk())
            // note, this is testing the JSON properties with the specific names defined in Student class
            .andExpect(jsonPath("$.name", is("Urist McTester")))
            .andExpect(jsonPath("$.SSN", is("12345")))
            .andExpect(jsonPath("$.DOB", is("2010-04-05")));

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(mStudentRepository).addStudent(studentCaptor.capture());

        assertEquals("The student repository wasn't called to add the student",
                "Urist McTester", studentCaptor.getValue().getName());
    }

    @Test
    public void addStudentMissingParametersBadRequest() throws Exception {
        mockMvc.perform(post("/students/add")
                .param("name", "Urist McTester2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
