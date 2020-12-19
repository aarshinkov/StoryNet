package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.collections.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.stories.*;
import com.aarshinkov.web.storynet.repositories.*;
import com.aarshinkov.web.storynet.utils.*;
import java.sql.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class StoryServiceImpl implements StoryService
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private CategoriesRepository categoriesRepository;

  @Autowired
  private StoriesRepository storiesRepository;

  @Autowired
  private CommentsRepository commentsRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public ObjCollection<StoryEntity> getStories(Integer page, Integer limit, String category, Long userId)
  {
    try (Connection conn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement cstmt = conn.prepareCall("{? = call get_stories(?, ?, ?, ?, ?)}"))
    {
      conn.setAutoCommit(false);

      cstmt.setInt(1, page);
      cstmt.setInt(2, limit);

      if (StringUtils.isEmpty(category))
      {
        cstmt.setString(3, null);
      }
      else
      {
        cstmt.setString(3, category);
      }

      if (userId == null)
      {
        cstmt.setNull(4, Types.BIGINT);
      }
      else
      {
        cstmt.setLong(4, userId);
      }

      cstmt.registerOutParameter(5, Types.BIGINT);
      cstmt.registerOutParameter(6, Types.REF_CURSOR);

      cstmt.execute();

      Long globalCount = cstmt.getLong(5);
      ResultSet rset = (ResultSet) cstmt.getObject(6);

      ObjCollection<StoryEntity> collection = new StoriesCollection<>();

      while (rset.next())
      {
        StoryEntity story = new StoryEntity();
        story.setStoryId(rset.getLong("story_id"));
        story.setTitle(rset.getString("title"));
        story.setStory(rset.getString("story"));
        story.setRating(rset.getDouble("rating"));
        story.setVisits(rset.getLong("visits"));
        story.setAnonymous(rset.getBoolean("anonymous"));
        story.setCreatedOn(rset.getTimestamp("created_on"));
        story.setEditedOn(rset.getTimestamp("edited_on"));

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(rset.getLong("category_id"));
        categoryEntity.setName(rset.getString("name"));

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(rset.getLong("user_id"));
        userEntity.setFirstName(rset.getString("first_name"));
        userEntity.setLastName(rset.getString("last_name"));

        story.setCategory(categoryEntity);
        story.setUser(userEntity);

        collection.getCollection().add(story);
      }

      long collectionCount = collection.getCollection().size();

      int start = (page - 1) * limit + 1;
      int end = start + collection.getCollection().size() - 1;

      Page pageWrapper = new PageImpl();
      pageWrapper.setCurrentPage(page);
      pageWrapper.setMaxElementsPerPage(limit);
      pageWrapper.setStartPage(start);
      pageWrapper.setEndPage(end);
      pageWrapper.setLocalTotalElements(collectionCount);
      pageWrapper.setGlobalTotalElements(globalCount);

      collection.setPage(pageWrapper);

      conn.commit();

      return collection;
    }
    catch (Exception e)
    {
      LOG.error("Error getting stories!", e);
    }

    return null;
  }

  @Override
  public StoryEntity getStoryByStoryId(Long storyId)
  {
    return storiesRepository.findByStoryId(storyId);
  }

  @Override
  public StoryEntity createStory(StoryCreateModel scm) throws Exception
  {
    UserEntity author = usersRepository.findByUserId(scm.getUserId());

    if (author == null)
    {
      throw new Exception("User does not exist");
    }

    CategoryEntity category = categoriesRepository.findByCategoryId(scm.getCategoryId());

    if (category == null)
    {
      throw new Exception("Category does not exist");
    }

    StoryEntity createStory = new StoryEntity();
    createStory.setTitle(scm.getTitle());
    createStory.setStory(scm.getStory());
    createStory.setAnonymous(scm.getAnonymous());
    createStory.setCategory(category);
    createStory.setUser(author);

    StoryEntity savedStory = storiesRepository.save(createStory);

    return savedStory;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public StoryEntity updateStory(Long storyId, StoryEditModel sem) throws Exception
  {
    StoryEntity story = storiesRepository.findByStoryId(storyId);

    if (story == null)
    {
      throw new Exception("Story with ID " + storyId + " does not exist");
    }

    CategoryEntity category = categoriesRepository.findByCategoryId(sem.getCategoryId());

    if (category == null)
    {
      throw new Exception("Category with ID " + sem.getCategoryId() + " does not exist");
    }

    story.setTitle(sem.getTitle());
    story.setStory(sem.getStory());
    story.setAnonymous(sem.getAnonymous());
    story.setCategory(category);
    story.setEditedOn(new Timestamp(System.currentTimeMillis()));

    StoryEntity updatedStory = storiesRepository.save(story);

    return updatedStory;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public StoryEntity deleteStory(Long storyId) throws Exception
  {
    StoryEntity story = storiesRepository.findByStoryId(storyId);

    if (story == null)
    {
      throw new Exception("Story with ID " + storyId + " does not exist");
    }

    storiesRepository.delete(story);

    return story;
  }

  @Override
  public Long getStoriesCountByCategory(Long categoryId) throws Exception
  {
    CategoryEntity category = categoriesRepository.findByCategoryId(categoryId);

    if (category == null)
    {
      throw new Exception("Category with ID " + categoryId + " does not exist");
    }

    return storiesRepository.countByCategory(category);
  }

  @Override
  public Long getStoriesCount()
  {
    return storiesRepository.count();
  }

  @Override
  public List<CommentEntity> getStoryComments(Long storyId, Integer page, Integer limit)
  {
    try (Connection conn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement cstmt = conn.prepareCall("{? = call get_comments(?, ?, ?)}"))
    {
      conn.setAutoCommit(false);

      cstmt.setLong(1, storyId);
      cstmt.setInt(2, page);
      cstmt.setInt(3, limit);

      cstmt.registerOutParameter(4, Types.REF_CURSOR);
      cstmt.execute();

      List<CommentEntity> storyComments = new ArrayList<>();

      ResultSet rset = (ResultSet) cstmt.getObject(4);

      while (rset.next())
      {
        CommentEntity comment = new CommentEntity();
        comment.setCommentId(rset.getLong("comment_id"));
        comment.setContent(rset.getString("content"));

        UserEntity user = new UserEntity();
        user.setUserId(rset.getLong("user_id"));
        user.setFirstName(rset.getString("first_name"));
        user.setLastName(rset.getString("last_name"));

        comment.setUser(user);

        comment.setCreatedOn(rset.getTimestamp("created_on"));
        comment.setEditedOn(rset.getTimestamp("edited_on"));

        storyComments.add(comment);
      }

      return storyComments;
    }
    catch (Exception e)
    {
      LOG.error("Error getting story's comments", e);
    }

    return null;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public CommentEntity createComment(CommentCreateModel ccm) throws Exception
  {
    CommentEntity comment = new CommentEntity();
    comment.setContent(ccm.getComment());

    StoryEntity story = storiesRepository.findByStoryId(ccm.getStoryId());

    if (story == null)
    {
      throw new Exception("Story with ID " + ccm.getStoryId() + " does not exist");
    }

    UserEntity user = usersRepository.findByUserId(ccm.getUserId());

    if (user == null)
    {
      throw new Exception("User with ID " + ccm.getUserId() + " does not exist");
    }

    comment.setStory(story);
    comment.setUser(user);

    CommentEntity createdComment = commentsRepository.save(comment);

    return createdComment;
  }

  @Override
  public Long getStoryCommentsCount(Long storyId)
  {
    return commentsRepository.countByStoryStoryId(storyId);
  }
}
