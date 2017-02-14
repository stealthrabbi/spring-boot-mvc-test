package org.webapp.example.school.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.webapp.example.school.AbstractSpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * Base spring test that uses an autowired MockMVC bean, and some helper methods for rebuilding JSON strings
 * in to java objects and lists of java objects.
 */
@AutoConfigureMockMvc
public abstract class AbstractControllerTest extends AbstractSpringBootTest {

    @Autowired
    protected MockMvc mMockMvc;

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
