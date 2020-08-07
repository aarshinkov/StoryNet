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
    
    List<Integer> numbersList = new ArrayList<>();
    numbersList.add(2);
    numbersList.add(5);
    numbersList.add(1);
    numbersList.add(7);
    numbersList.add(9);
    numbersList.add(15);

    List<String> names = new ArrayList<>();
    names.add("Atanas");
    names.add("Stefani");
    names.add("Georgi");
    
    model.addAttribute("list", numbersList);
    model.addAttribute("names", names);

    return "home";
  }
}
