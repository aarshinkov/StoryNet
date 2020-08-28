package com.aarshinkov.web.storynet.domain;

import java.io.*;
import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameDomain implements Serializable
{
  private String firstName;
  private String lastName;

  public String getFullName()
  {
    return (lastName != null) ? firstName + ' ' + lastName : firstName;
  }

  @Override
  public String toString()
  {
    return getFullName();
  }
}
