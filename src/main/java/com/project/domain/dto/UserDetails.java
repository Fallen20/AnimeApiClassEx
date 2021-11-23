package com.project.domain.dto;

import java.util.UUID;

public class UserDetails {
    public UUID userid;
    public String username;

    public UserDetails(UUID userid, String username) {
        this.userid = userid;
        this.username = username;
    }
}
