package com.project.domain.dto;

import java.util.UUID;

public class UserResult {

    public UUID userid;
    public String username;

    public UserResult(UUID userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
