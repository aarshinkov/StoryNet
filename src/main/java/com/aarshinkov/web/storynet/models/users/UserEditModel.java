package com.aarshinkov.web.storynet.models.users;

import java.io.*;
import javax.validation.constraints.*;
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
@ToString
public class UserEditModel implements Serializable
{
  private Long userId;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String firstName;

  private String lastName;

  @NotEmpty
  @Email
  @Size(max = 200)
  private String email;

  public String getFullName()
  {
    return (lastName != null) ? firstName + ' ' + lastName : firstName;
  }
}
