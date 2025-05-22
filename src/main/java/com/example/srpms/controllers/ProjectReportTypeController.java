package com.example.srpms.controllers;

import com.example.srpms.models.Personneltype;
import com.example.srpms.models.Projectreporttype;
import com.example.srpms.repository.ProjectReportTypeRepository;
import com.example.srpms.services.PersonnelTypeService;
import com.example.srpms.services.ProjectReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project-report-type")
public class ProjectReportTypeController {
    @Autowired
    private ProjectReportTypeService projectReportTypeService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","project-report-type");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("projectReportType", projectReportTypeService.getAll());
        return "projectreporttypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("projectReportType", new Projectreporttype());
        return "projectreporttypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Projectreporttype projectreporttype, Model model) {
        activePage(model);
        projectReportTypeService.add(projectreporttype);
        return "redirect:/project-report-type/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Projectreporttype existing = projectReportTypeService.getById(id);
        model.addAttribute("projectReportType", existing);
        return "projectreporttypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Projectreporttype projectreporttype, Model model) {
        activePage(model);
        projectreporttype.setId(id);
        projectReportTypeService.update(projectreporttype);
        return "redirect:/project-report-type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        projectReportTypeService.deleteById(id);
        return "redirect:/project-report-type/list";
    }
}
