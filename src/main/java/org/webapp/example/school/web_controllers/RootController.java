package org.webapp.example.school.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webapp.example.school.AppPropertiesContainer;

/**
 * Created by Mark on 1/22/2017.
 */
@Controller
@RequestMapping("")
public class RootController {

    @Autowired
    private AppPropertiesContainer mAppPropertiesContainer;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String greeting() {

        String webAppName = String.format("%s (%s}", mAppPropertiesContainer.getShortAppName(),
                mAppPropertiesContainer.getAppName());
        return "If you're reading this, the " + webAppName +  "is on. <BR><BR>" +
                "Trying viewing students <a href = students/list>here</a> <BR><BR>" +
                "Or viewing a <a href = students/Steve>specific student</a>.";
    }
}
