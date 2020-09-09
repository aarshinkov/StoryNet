package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.base.*;
import com.aarshinkov.web.storynet.services.*;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
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
  public SystemService systemService;

  @GetMapping(value = "/profile")
  public String profile(HttpServletRequest request)
  {
    long userId = (long) systemService.getSessionAttribute(request, "userId");

    return "profile/profile";
  }
}
