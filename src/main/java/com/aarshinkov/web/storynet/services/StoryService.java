package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.collections.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.stories.*;
import java.util.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface StoryService
{
  ObjCollection<StoryEntity> getStories(Integer page, Integer limit, String category, Long userId);

  StoryEntity getStoryByStoryId(Long storyId);

  StoryEntity createStory(StoryCreateModel scm) throws Exception;

  StoryEntity updateStory(Long storyId, StoryEditModel sem) throws Exception;

  StoryEntity deleteStory(Long storyId) throws Exception;

  void readStory(Long storyId) throws Exception;

  Long getStoriesCountByCategory(Long categoryId) throws Exception;

  Long getStoriesCount();

  List<CommentEntity> getStoryComments(Long storyId, Integer page, Integer limit);

  CommentEntity createComment(CommentCreateModel ccm) throws Exception;

  Long getStoryCommentsCount(Long storyId);
}
