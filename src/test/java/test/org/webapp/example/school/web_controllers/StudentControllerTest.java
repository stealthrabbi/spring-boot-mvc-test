package test.org.webapp.example.school.web_controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MvcResult;
import org.webapp.example.school.data_repository.StudentRepository;
import org.webapp.example.school.domain_model.Student;
import org.webapp.example.school.web_controllers.StudentController;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mark on 1/22/2017.
 */
public class StudentControllerTest extends AbstractControllerTest {

    @Mock
    private StudentRepository mStudentRepository;

    @InjectMocks
    private StudentController mStudentController;

    private List<Student> mStudentList;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        setUpMvc(mStudentController);

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
            Assert.assertEquals(mStudentList.get(i).getmName(), student1.getmName());
        }
    }

    @Test
    public void getStudentByName_ValidName() throws Exception {
        MvcResult result =
            mockMvc.perform(get("/students/{name}", "Peter Venkman"))
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Peter Venkman")))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getStudentByName_InValidName() throws Exception {
        MvcResult result =
            mockMvc.perform(get("/students/{name}", "Random Name"))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn();

    }
}
