package com.aarshinkov.web.storynet.validations;

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
public class Person
{
//  @NotEmpty
  @Size(min = 2, max = 100)
  private String firstName;

  @Size(min = 2, max = 100)
  private String lastName;

  @Min(1)
//  @Max(250)
  private Integer age;
}
