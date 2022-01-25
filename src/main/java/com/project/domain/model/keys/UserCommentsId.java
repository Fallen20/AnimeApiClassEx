package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;


public class UserCommentsId implements Serializable {
    public UUID commentid;
    public UUID userid;


    public UserCommentsId(){}

    public UserCommentsId(UUID commentid, UUID userid) {
        this.commentid = commentid;
        this.userid = userid;
    }
}
