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
public class HomeController
{
  @GetMapping(value = "/")
  public String home(Model model)
  {
    double a = 5.8;
    double b = 8.2;

    String name = "Georgi Petkov";
    model.addAttribute("name", name);

    model.addAttribute("result", a * b);

    return "home";
  }
}
