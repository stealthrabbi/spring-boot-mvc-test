package org.webapp.example.school;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:webapp.properties")
public class AppPropertiesContainer {

    @Value("${app.name}")
    private String mAppName;

    @Value("${app.name.short}")
    private String mShortAppName;

    public String getAppName() {
        return mAppName;
    }

    public String getShortAppName() {
        return mShortAppName;
    }
}
