package com.aarshinkov.web.storynet.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
public class TestController
{
  @GetMapping(value = "/testForm1")
  public String testForm1()
  {
    return "test/testForm";
  }

  @GetMapping(value = "/submitForm")
  public String submitForm1(@RequestParam(name = "firstName") String firstName,
          @RequestParam(name = "lastName") String lastName, Model model)
  {
    model.addAttribute("firstName", firstName);
    model.addAttribute("lastName", lastName);

    return "test/submitForm";
  }
}
