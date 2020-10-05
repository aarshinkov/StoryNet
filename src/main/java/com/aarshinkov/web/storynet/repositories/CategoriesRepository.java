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
public interface CategoriesRepository extends JpaRepository<CategoryEntity, Long>
{
  CategoryEntity findByCategoryId(Long categoryId);
}
