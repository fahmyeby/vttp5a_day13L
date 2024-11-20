package nus.edu.iss.vttp_day13l.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import nus.edu.iss.vttp_day13l.models.Person;
import nus.edu.iss.vttp_day13l.services.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;
  
    @GetMapping("")
    public String personListing(Model model) {
      List<Person> persons = personService.findAll();
      model.addAttribute("persons", persons);
  
      return "list";
    }
  
    @GetMapping("/create")
    public String createForm(Model model) {
      Person p = new Person();
      model.addAttribute("persons", p);
        return "create";
    }
    
  @PostMapping("/create")
  public String createPerson(@Valid @ModelAttribute("persons") Person person,  BindingResult result, Model model) {
      if (result.hasErrors()) {
          return "create";  // Show the form again with validation errors
      }
      // Save the person or perform other actions
      Person p = new Person(person.getFirstName(),person.getLastName(),person.getSalary(),person.getEmail(),person.getDateOfBirth(),person.getPhoneNumber(),person.getPostalCode());
      personService.create(p);
      return "redirect:/persons";  // Redirect after successful creation
  }
  
  
  
  @GetMapping("/delete/{person-id}")
  public String deletePerson(@PathVariable("person-id") String personId) {
      Person p = personService.findById(personId);
      personService.delete(p);
  
      return "redirect:/persons";
  }
  
  
  @GetMapping("/update/{person-id}")
  public String updateForm(@PathVariable("person-id") String personId, Model model) {
      Person p = personService.findById(personId);
      if (p == null) {
          return "error"; // Return an error page if the person doesn't exist
      }
      model.addAttribute("person", p);
      return "update";
  }
  
  
  
  @PostMapping("/update")
  public String posteditPerson(@Valid @ModelAttribute("persons") Person person,  BindingResult result, Model model) {
    if (result.hasErrors()) 
      return "update";  // Show the form again with validation errors
  
  personService.update(person);
  
      return "redirect:/persons";
  
  }
    
  }
