package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.repositories.*;
import com.aarshinkov.web.storynet.validations.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
public class TestController
{
  @Autowired
  private RolesRepository rolesRepository;

  @GetMapping(value = "/testForm1")
  public String testForm1(Model model)
  {
    model.addAttribute("person", new Person());

    return "test/testForm";
  }

  @PostMapping(value = "/testForm1")
  public String submitForm1(@Valid Person person, BindingResult bindingResult, Model model)
  {
    if (bindingResult.hasErrors())
    {
      System.out.println("errors: " + bindingResult);
      model.addAttribute("person", person);
      return "test/testForm";
    }

    model.addAttribute("person", person);
    person.getFirstName();

    return "test/submitForm";
  }

  @ResponseBody
  @GetMapping(value = "/testRole/{rolename}")
  public RoleEntity getRole(@PathVariable("rolename") String rolename)
  {
    return rolesRepository.findByRolename(rolename);
  }
}
