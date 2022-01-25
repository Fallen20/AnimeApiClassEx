package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;


public class AnimeUserDoubleId implements Serializable {
    public UUID userid;
    public UUID animeid;

    public AnimeUserDoubleId(){}

    public AnimeUserDoubleId(UUID userid, UUID animeid) {
        this.userid = userid;
        this.animeid = animeid;
    }
}
