package com.aarshinkov.web.storynet.controllers;

import java.util.*;
import org.slf4j.*;
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
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @GetMapping(value = "/")
  public String home(Model model)
  {
    model.addAttribute("globalMenu", "home");

    return "home";
  }

  @GetMapping(value = "/printName/{firstName}/{lastName}")
  public String printName(@PathVariable("firstName") String firstName,
          @PathVariable("lastName") String lastName, Model model)
  {
    model.addAttribute("firstName", firstName);
    model.addAttribute("lastName", lastName);

    return "printName";
  }
}
