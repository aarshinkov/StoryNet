package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.base.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;
import com.aarshinkov.web.storynet.services.*;
import javax.servlet.http.*;
import javax.validation.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
public class LoginController extends Base
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserService userService;

  @GetMapping(value = "/login")
  public String prepareLogin(HttpServletRequest request, Model model)
  {
    model.addAttribute("badCredentials", request.getParameter("badCredentials") != null);

    model.addAttribute("globalMenu", "login");

    return "auth/login";
  }

  @GetMapping(value = "/signup")
  public String prepareSignup(Model model)
  {
    model.addAttribute("user", new UserCreateModel());

    model.addAttribute("globalMenu", "signup");

    return "auth/signup";
  }

  @PostMapping(value = "/signup")
  public String signup(@ModelAttribute("user") @Valid UserCreateModel ucm,
          BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model)
  {
    if (userService.isUserExistByEmail(ucm.getEmail()))
    {
      bindingResult.rejectValue("email", "signup.email.exist");
    }

    if (!ucm.getPassword().equals(ucm.getConfirmPassword()))
    {
      bindingResult.rejectValue("password", "signup.password.nomatch");
      bindingResult.rejectValue("confirmPassword", "signup.password.nomatch");
    }

    if (bindingResult.hasErrors())
    {
      model.addAttribute("globalMenu", "signup");

      return "auth/signup";
    }

    try
    {
      UserEntity createdUser = userService.createUser(ucm);
      redirectAttributes.addFlashAttribute("msgSuccess", getMessage("signup.success", createdUser.getFirstName() + " " + createdUser.getLastName()));
    }
    catch (Exception e)
    {
      redirectAttributes.addFlashAttribute("msgError", getMessage("signup.error"));
    }

    return "redirect:/";
  }
}
