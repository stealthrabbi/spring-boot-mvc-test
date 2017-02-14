package org.webapp.example.school.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.webapp.example.school.AppPropertiesContainer;

/**
 * Controller handling request for the root of the REST interface.
 */
@RestController
@RequestMapping("")
public class RootController {

    @Autowired
    private AppPropertiesContainer mAppPropertiesContainer;

    /**
     * Returns basic greeting for browsing the root.
     * @return the HTML text to render
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String greeting() {

        String webAppName = String.format("%s (%s}", mAppPropertiesContainer.getShortAppName(),
                mAppPropertiesContainer.getAppName());
        return "If you're reading this, the " + webAppName +  "is on. <BR><BR>"
                + "Trying viewing students <a href = students/list>here</a> <BR><BR>"
                + "Or viewing a <a href = students/Steve>specific student</a>. <BR><BR>"
                + "For extra credit, here's an <a href = student-records.html>HTML page making a rest call using jQuery</a>";
    }
}
