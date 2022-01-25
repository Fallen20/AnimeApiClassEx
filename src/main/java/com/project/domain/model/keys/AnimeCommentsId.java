package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;


public class AnimeCommentsId implements Serializable {
    public UUID commentid;
    public UUID animeid;


    public AnimeCommentsId(){}

    public AnimeCommentsId(UUID commentid, UUID animeid) {
        this.commentid = commentid;
        this.animeid = animeid;
    }
}
