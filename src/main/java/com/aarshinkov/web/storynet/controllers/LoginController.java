package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;
import com.aarshinkov.web.storynet.services.*;
import javax.validation.*;
import org.slf4j.*;
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
public class LoginController
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserService userService;

  @GetMapping(value = "/signup")
  public String prepareSignup(Model model)
  {
    model.addAttribute("user", new UserCreateModel());

    return "auth/signup";
  }

  @PostMapping(value = "/signup")
  public String signup(@ModelAttribute("user") @Valid UserCreateModel ucm,
          BindingResult bindingResult, Model model)
  {
    if (!ucm.getPassword().equals(ucm.getConfirmPassword()))
    {
      bindingResult.rejectValue("password", "signup.password.nomatch");
      bindingResult.rejectValue("confirmPassword", "signup.password.nomatch");
    }

    if (bindingResult.hasErrors())
    {
      return "auth/signup";
    }

    UserEntity createdUser = userService.createUser(ucm);
    
    return "redirect:/";
  }
}