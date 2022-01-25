package com.project.domain.model;

import com.project.domain.model.keys.UserCommentsId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="comment_by_user")
@IdClass(UserCommentsId.class)
public class CommentUser {

    @Id
    public UUID commentid;
    @Id
    public UUID userid;

}