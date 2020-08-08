package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.repositories.*;
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
public class UsersController
{
  @Autowired
  private UsersRepository usersRepository;

  @GetMapping(value = "/user/{userId}")
  public String getUser(@PathVariable("userId") Long userId, Model model)
  {
    UserEntity user = usersRepository.findByUserId(userId);
    model.addAttribute("user", user);

    return "users/user";
  }
}
