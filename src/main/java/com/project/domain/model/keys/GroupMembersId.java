package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;

public class GroupMembersId implements Serializable {

    public UUID groupid;
    public UUID userid;

    public GroupMembersId() {
    }

    public GroupMembersId(UUID groupid, UUID userid) {
        this.groupid = groupid;
        this.userid = userid;
    }
}
