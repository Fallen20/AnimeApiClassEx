package com.project.domain.model.keys;

import java.io.Serializable;
import java.util.UUID;

public class FollowsIdClass  implements Serializable {

    public UUID actual_user;
    public UUID users_followed;

    public FollowsIdClass(){}

    public FollowsIdClass(UUID userid1, UUID userid2) {
        this.actual_user = userid1;
        this.users_followed = userid2;
    }
}
