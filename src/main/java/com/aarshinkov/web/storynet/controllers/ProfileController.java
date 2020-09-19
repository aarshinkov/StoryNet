package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.base.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;
import com.aarshinkov.web.storynet.services.*;
import javax.servlet.http.*;
import javax.validation.*;
import org.slf4j.*;
import org.slf4j.Logger;
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
public class ProfileController extends Base
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  public UserService userService;

  @Autowired
  public SystemService systemService;

  @GetMapping(value = "/profile")
  public String profile(HttpServletRequest request, Model model)
  {
    long userId = (long) systemService.getSessionAttribute(request, "userId");

    model.addAttribute("globalMenu", "profile");
    model.addAttribute("submenu", "profile");

    try
    {
      UserEntity user = userService.getUserByUserId(userId);
      model.addAttribute("user", user);
    }
    catch (Exception e)
    {
      return "redirect:/";
    }

    return "profile/profile";
  }

  @GetMapping(value = "/profile/edit")
  public String prepareProfileEdit(HttpServletRequest request, Model model) throws Exception
  {
    long userId = (long) systemService.getSessionAttribute(request, "userId");

    UserEntity storedUser = userService.getUserByUserId(userId);

    UserEditModel uem = new UserEditModel();
    uem.setUserId(storedUser.getUserId());
    uem.setFirstName(storedUser.getFirstName());
    uem.setLastName(storedUser.getLastName());
    uem.setEmail(storedUser.getEmail());

    model.addAttribute("uem", uem);
    model.addAttribute("globalMenu", "profile");
    model.addAttribute("submenu", "profile");

    return "profile/edit";
  }

  @PostMapping(value = "/profile/edit")
  public String editProfile(@ModelAttribute("uem") @Valid UserEditModel uem, BindingResult bindingResult,
          RedirectAttributes redirectAttributes, Model model)
  {
    if (bindingResult.hasErrors())
    {
      model.addAttribute("globalMenu", "profile");
      model.addAttribute("submenu", "profile");
      return "profile/edit";
    }

    try
    {
      UserEntity updatedUser = userService.updateUser(uem);
      
      redirectAttributes.addFlashAttribute("msgSuccess", getMessage("profile.edit.success"));
    }
    catch (Exception e)
    {
      LOG.error("Error updating user", e);
      redirectAttributes.addFlashAttribute("msgError", getMessage("profile.edit.error"));
    }

    return "redirect:/profile";
  }
}
