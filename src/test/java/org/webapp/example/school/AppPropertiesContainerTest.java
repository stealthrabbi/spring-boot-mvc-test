package org.webapp.example.school;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppPropertiesContainerTest extends AbstractSpringBootTest {

    @Autowired
    private AppPropertiesContainer mAppPropertiesContainer;

    @Test
    public void verifyName() {
        Assert.assertEquals("Test App", mAppPropertiesContainer.getAppName());
    }

    @Test
    public void verifyShortName() {
        Assert.assertEquals("TA", mAppPropertiesContainer.getShortAppName());
    }
}
