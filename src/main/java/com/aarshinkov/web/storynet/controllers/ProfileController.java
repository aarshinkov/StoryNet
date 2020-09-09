package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.base.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.services.*;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

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
}
