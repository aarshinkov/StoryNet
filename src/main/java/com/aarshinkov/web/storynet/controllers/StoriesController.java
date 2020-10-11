package com.aarshinkov.web.storynet.controllers;

import com.aarshinkov.web.storynet.base.*;
import com.aarshinkov.web.storynet.collections.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.stories.*;
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
import org.thymeleaf.util.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
public class StoriesController extends Base
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private StoryService storyService;

  @GetMapping(value = "/stories")
  public String getStories(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
          @RequestParam(name = "limit", defaultValue = "5", required = false) Integer limit,
          @RequestParam(name = "cat", defaultValue = "", required = false) String category,
          Model model)
  {
    ObjCollection<StoryEntity> stories = storyService.getStories(page, limit, category, null);

    model.addAttribute("storiesCount", stories.getPage().getLocalTotalElements());

    model.addAttribute("stories", stories.getCollection());

    String otherParams = "";

    if (limit != null && limit > 0)
    {
      otherParams = "&limit=" + limit;
    }

    if (!StringUtils.isEmpty(category))
    {
      otherParams += "&cat=" + category;
    }

    model.addAttribute("otherParameters", otherParams);

    model.addAttribute("pageWrapper", stories.getPage());
    model.addAttribute("maxPagesPerView", 5);

    model.addAttribute("cat", category);

    model.addAttribute("globalMenu", "stories");

    return "stories/stories";
  }

  @GetMapping(value = "/story/{storyId}")
  public String getStory(@PathVariable(value = "storyId") Long storyId, Model model)
  {
    StoryEntity story = storyService.getStoryByStoryId(storyId);

    model.addAttribute("story", story);

    model.addAttribute("globalMenu", "stories");

    return "stories/story";
  }

  @GetMapping(value = "/story/create")
  public String prepareCreateStory(StoryCreateModel scm, Model model)
  {
    model.addAttribute("story", scm);

    model.addAttribute("globalMenu", "stories");

    return "stories/create";
  }

  @PostMapping(value = "/story/create")
  public String createStory(@ModelAttribute("story") @Valid StoryCreateModel scm, BindingResult bindingResult,
          HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
  {
    if (bindingResult.hasErrors())
    {
      model.addAttribute("globalMenu", "stories");
      return "stories/create";
    }

    try
    {
      StoryEntity createStory = storyService.createStory(scm);
      redirectAttributes.addFlashAttribute("msgSuccess", getMessage("story.create.success"));
    }
    catch (Exception e)
    {
      redirectAttributes.addFlashAttribute("msgError", getMessage("story.create.erorr"));
    }

    return "redirect:/stories";
  }

  @GetMapping(value = "/story/edit/{storyId}")
  public String prepareEditStory(@PathVariable(value = "storyId") Long storyId, HttpServletRequest request, Model model)
  {
    StoryEntity storedStory = storyService.getStoryByStoryId(storyId);

    StoryEditModel story = new StoryEditModel();
    story.setTitle(storedStory.getTitle());
    story.setStory(storedStory.getStory());
    story.setAnonymous(storedStory.getAnonymous());
    story.setCategoryId(storedStory.getCategory().getCategoryId());

    model.addAttribute("story", story);
    model.addAttribute("storyId", storyId);

    model.addAttribute("globalMenu", "stories");

    return "stories/edit";
  }
}
