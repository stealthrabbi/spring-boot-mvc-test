package org.webapp.example.school.web.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.webapp.example.school.AppPropertiesContainer;


/**
 * Tests the {@link RootController} using a mocked bean for the {@link AppPropertiesContainer}.
 */
public class RootControllerTest extends AbstractControllerTest {

    @MockBean
    private AppPropertiesContainer mAppPropertiesContainer;

    @InjectMocks
    private RootController mRootController;

    private static final String TEST_NAME = "AAAAAAAAAA";
    private static final String TEST_SHORT_NAME = "BBB";

    @Before
    public void setUp() {
        when(mAppPropertiesContainer.getAppName()).thenReturn(TEST_NAME);
        when(mAppPropertiesContainer.getShortAppName()).thenReturn(TEST_SHORT_NAME);
    }

    /**
     * Verifies the RootController's greeting message contains the application names as provided by
     * its spring bean for the app properties container.
     * @throws Exception exception occurs
     */
    @Test
    public void greeting() throws Exception {

        mMockMvc.perform(get("/test-load"))
                .andDo(print())
                .andExpect(content().string(containsString(TEST_NAME)))
                .andExpect(content().string(containsString(TEST_SHORT_NAME)))
                .andExpect(status().isOk());
    }
}
