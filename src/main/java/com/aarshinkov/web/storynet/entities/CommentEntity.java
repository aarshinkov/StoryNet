package com.aarshinkov.web.storynet.entities;

import com.fasterxml.jackson.annotation.*;
import java.io.*;
import java.sql.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

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
@Table(name = "comments")
@DynamicInsert
@DynamicUpdate
public class CommentEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "seq_gen_comment", sequenceName = "s_comments", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_comment")
  @Column(name = "comment_id")
  private Long commentId;

  @Column(name = "content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "story_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private StoryEntity story;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private UserEntity user;

  @Column(name = "created_on")
  private Timestamp createdOn;

  @Column(name = "edited_on")
  private Timestamp editedOn;
}
