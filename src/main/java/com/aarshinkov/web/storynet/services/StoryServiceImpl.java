package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.stories.*;
import com.aarshinkov.web.storynet.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class StoryServiceImpl implements StoryService
{
  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private CategoriesRepository categoriesRepository;

  @Autowired
  private StoriesRepository storiesRepository;

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
}
