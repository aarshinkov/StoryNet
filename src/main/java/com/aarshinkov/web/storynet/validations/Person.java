package com.aarshinkov.web.storynet.validations;

import javax.validation.constraints.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
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

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public Integer getAge()
  {
    return age;
  }

  public void setAge(Integer age)
  {
    this.age = age;
  }
}
