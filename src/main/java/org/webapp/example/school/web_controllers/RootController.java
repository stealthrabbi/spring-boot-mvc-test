package org.webapp.example.school.web_controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mark on 1/22/2017.
 */
@Controller
@RequestMapping("")
public class RootController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String greeting() {
        return "If you're reading this, the web app is on. <BR><BR> Trying viewing students <a href = students/list>here</a>";
    }
}
