package com.project.domain.model;

import com.project.domain.model.keys.FollowsIdClass;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="user_follows")
@IdClass(FollowsIdClass.class)
public class FollowsUsers {

    @Id
    public UUID actual_user;
    @Id
    public UUID users_followed;

}
