package com.aarshinkov.web.storynet.collections;

import com.aarshinkov.web.storynet.utils.*;
import java.util.*;
import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@ToString
public abstract class ObjCollection<T>
{
  private List<T> collection = new ArrayList<>();
  private Page page;

  public List<T> getCollection()
  {
    return collection;
  }

  public void setCollection(List<T> collection)
  {
    this.collection = collection;
  }

  public Page getPage()
  {
    return page;
  }

  public void setPage(Page page)
  {
    this.page = page;
  }
}
