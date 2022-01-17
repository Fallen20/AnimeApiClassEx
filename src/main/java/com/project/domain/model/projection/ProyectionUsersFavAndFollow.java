package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProyectionUsersFavAndFollow {
    UUID getUserid();
    String getUsername();

    @JsonIgnoreProperties("usersFavourites")
    Set<ProyectionUserAnime> getFavourites();

    Set<ProyectionFollows> getFollows();
}
