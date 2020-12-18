package com.aarshinkov.web.storynet.services;

import org.slf4j.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class CommentServiceImpl implements CommentService
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private CommentsRepository commentsRepository;

  @Override
  public CommentEntity getCommentByCommentId(Long commentId) throws Exception
  {
    return commentsRepository.findByCommentId(commentId);
  }

  @Override
  public CommentEntity deleteComment(Long commentId) throws Exception
  {
    CommentEntity comment = commentsRepository.findByCommentId(commentId);

    if (comment == null)
    {
      throw new Exception("Comment with ID " + commentId + " does not exist");
    }

    commentsRepository.delete(comment);

    return comment;
  }
}
