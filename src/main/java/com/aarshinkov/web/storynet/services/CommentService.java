package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface CommentService
{
  CommentEntity getCommentByCommentId(Long commentId) throws Exception;

  CommentEntity deleteComment(Long commentId) throws Exception;
}
