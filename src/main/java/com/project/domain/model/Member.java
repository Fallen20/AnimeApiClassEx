package com.project.domain.model;

import com.project.domain.model.keys.GroupMembersId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="miembros_grupo")
@IdClass(GroupMembersId.class)
public class Member {
    @Id
    public UUID groupid;

    @Id
    public UUID userid;
}
