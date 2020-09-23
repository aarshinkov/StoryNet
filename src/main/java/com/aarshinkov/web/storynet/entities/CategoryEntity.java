package com.aarshinkov.web.storynet.entities;

import java.io.*;
import java.sql.*;
import javax.persistence.*;
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
@Entity
@Table(name = "categories")
public class CategoryEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "seq_gen_category", sequenceName = "s_categories", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_category")
  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "name")
  private String name;

  @Column(name = "created_on")
  private Timestamp createdOn;
}
