package com.aarshinkov.web.storynet.security;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.services.*;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class Expressions
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private SystemService systemService;

  @Autowired
  private StoryService storyService;

  @Autowired
  private CommentService commentService;

  public boolean isUserOwnerToComment(Long commentId, HttpServletRequest request) throws Exception
  {
    Long userId = (Long) systemService.getSessionAttribute(request, "userId");

    CommentEntity comment = commentService.getCommentByCommentId(commentId);

    if (comment == null)
    {
      throw new Exception("Comment with ID " + commentId + " does not exist");
    }

    UserEntity owner = comment.getUser();

    return owner.getUserId().equals(userId);
  }

  public boolean isUserOwner(Long storyId, HttpServletRequest request)
  {
    Long userId = (Long) systemService.getSessionAttribute(request, "userId");

    StoryEntity storedStory = storyService.getStoryByStoryId(storyId);
    UserEntity owner = storedStory.getUser();

    return owner.getUserId().equals(userId);
  }
}
