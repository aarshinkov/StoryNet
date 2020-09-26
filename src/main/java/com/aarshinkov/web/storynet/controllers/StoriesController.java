package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.base.*;
import com.aarshinkov.web.storynet.models.stories.*;
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
public class StoriesController extends Base
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @GetMapping(value = "/stories")
  public String getStories(Model model)
  {
    model.addAttribute("globalMenu", "stories");

    return "stories/stories";
  }

  @GetMapping(value = "/story/create")
  public String prepareCreateStory(StoryCreateModel scm, Model model)
  {
    model.addAttribute("story", scm);

    model.addAttribute("globalMenu", "stories");

    return "stories/create";
  }
}
