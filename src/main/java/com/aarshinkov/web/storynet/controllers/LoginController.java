package com.aarshinkov.web.storynet.controllers;

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
public class LoginController
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @GetMapping(value = "/signup")
  public String prepareSignup(Model model)
  {
    return "auth/signup";
  }
}
