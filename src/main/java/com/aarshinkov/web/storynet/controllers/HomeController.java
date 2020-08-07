package com.aarshinkov.web.storynet.controllers;

import org.springframework.stereotype.*;
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
  public String home()
  {
    return "home";
  }
}
