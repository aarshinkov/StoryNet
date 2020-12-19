package com.aarshinkov.web.storynet.repositories;

import com.aarshinkov.web.storynet.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Repository
public interface StoriesRepository extends JpaRepository<StoryEntity, Long>
{
  StoryEntity findByStoryId(Long storyId);

  Long countByCategory(CategoryEntity category);
}
