package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProyectionFollows {
    String getUsername();


    @JsonIgnoreProperties("follows")
    Set<ProyectionUser> getUser();
}
