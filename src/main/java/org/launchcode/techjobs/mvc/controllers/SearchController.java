package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 package org.launchcode.techjobs.mvc.controllers;

 import org.launchcode.techjobs.mvc.models.Job;
 import org.launchcode.techjobs.mvc.models.JobData;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.*;

 import javax.websocket.server.PathParam;

 import java.util.ArrayList;

 import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


 /**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController<Job> {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value = "results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        ArrayList<org.launchcode.techjobs.mvc.models.Job> jobs;
        if (Objects.equals(searchTerm, "all") || Objects.equals(searchTerm, "")){
            jobs = JobData.findAll();
            model.addAttribute("title", "Jobs With All:");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}