package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;


public class AnimeUserCommentId implements Serializable {
    public UUID commentid;
    public UUID userid;
    public UUID animeid;


    public AnimeUserCommentId(){}

    public AnimeUserCommentId(UUID commentid, UUID userid, UUID animeid) {
        this.commentid = commentid;
        this.userid = userid;
        this.animeid = animeid;
    }
}
