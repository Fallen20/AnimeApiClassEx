package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;


public class FavouriteIdClass implements Serializable {
    public UUID userid;
    public UUID animeid;

    public FavouriteIdClass(){}

    public FavouriteIdClass(UUID userid, UUID animeid) {
        this.userid = userid;
        this.animeid = animeid;
    }
}
