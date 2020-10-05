package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.stories.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface StoryService
{
  StoryEntity getStoryByStoryId(Long storyId);

  StoryEntity createStory(StoryCreateModel scm) throws Exception;
}
