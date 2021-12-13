package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProyectionUserAnime {
    UUID getAnimeid();
    String getName();

    @JsonIgnoreProperties("favourites")
    Set<ProyectionUser> getUsersFavourites();
}
