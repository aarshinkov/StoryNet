package com.aarshinkov.web.storynet.controllers;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
public class HomeController
{
  @GetMapping(value = "/")
  public String home(Model model)
  {
    return "home";
  }

  @GetMapping(value = "/printName")
  public String printName(@RequestParam(name = "name", defaultValue = "John") String name,
          @RequestParam(name = "age") Integer age, Model model)
  {
    model.addAttribute("name", name);
    model.addAttribute("age", age);

    return "printName";
  }
}
