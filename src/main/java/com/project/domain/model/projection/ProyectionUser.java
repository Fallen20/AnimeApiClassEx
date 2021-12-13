package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.domain.model.Anime;

import java.util.Set;
import java.util.UUID;

public interface ProyectionUser {
    UUID getUserid();
    String getUsername();

    @JsonIgnoreProperties("usersFavourites")
    Set<ProyectionUserAnime> getFavourites();
}
