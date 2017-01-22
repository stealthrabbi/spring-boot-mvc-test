package test.org.webapp.example.school.web_controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import test.org.webapp.example.school.AbstractSpringTest;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mark on 1/22/2017.
 */
@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractSpringTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected void setUpMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected void setUpMvc(Object controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Maps a String of JSON into an instance of a Class of type T. Uses a
     * Jackson ObjectMapper.
     * @param json A String of JSON.
     * @param clazz A Class of type T. The mapper will attempt to convert the
     *        JSON into an Object of this Class type.
     * @return An Object of type T.
     * @throws IOException Thrown if an error occurs while mapping.
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    /**
     * Maps the provided json string in to a List of the specified type, T
     * @param json A String of JSON
     * @param clazz A Class of type T
     * @param <T> The type T (same as clazz)
     * @return List of type T
     * @throws IOException Thrown if an error occurs while mapping.
     */
    protected <T> List<T> mapFromJsonToList(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
    }
}
