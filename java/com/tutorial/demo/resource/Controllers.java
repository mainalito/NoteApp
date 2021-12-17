package com.tutorial.demo.resource;

import com.tutorial.demo.Repository.Repository;
import com.tutorial.demo.models.Notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controllers {
    @Autowired
    Services service;
    @Autowired
    Repository repo;

    @GetMapping("/addNotes")
    public String showForm(Model model) {
        Notebook notebook = new Notebook();
        model.addAttribute("notes", notebook);
        return "add-notes";
    }

    @PostMapping("/addNotes") // addnotes will be used in thymleaf as action
    public String addNotes(@ModelAttribute("notes") Notebook notes) {

        System.out.println(notes);
        service.addNote(notes);
        //handle empty data by returning them to the same page
        return "redirect:/";
    }

    @GetMapping("/")
    public String showNotes(Model model) {
        model.addAttribute("notesFetched", service.getAllnotes());
        return "index";
    }

    @GetMapping("/showFormEdit/{id}")
    public String updateNotes(@PathVariable("id") Long id, Model model) {
        Notebook notebook = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note id:" + id));
        model.addAttribute("notes", notebook);
        return "updated_notes";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        Notebook note = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        repo.delete(note);
        return "redirect:/";
    }

}
