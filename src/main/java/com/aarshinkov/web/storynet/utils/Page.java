package com.aarshinkov.web.storynet.utils;

import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public abstract class Page
{
  protected Integer currentPage;
  protected Long localTotalElements;
  protected Long globalTotalElements;
  protected Integer maxElementsPerPage = 6;
  protected Integer startPage;
  protected Integer endPage;

  protected abstract Long getTotalPages();

  protected abstract boolean isFirst();

  protected abstract boolean isLast();

  protected abstract boolean hasNext(Integer currentPage);

  protected abstract boolean hasPrevious(Integer currentPage);
}
