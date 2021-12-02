package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGenresLimitedId {

    UUID getGenreid();
    String getLabel();
    //url no

    @JsonIgnoreProperties("genres")//el nombre es del get del otro
    Set<ProjectionAnimesLimitedId> getAnimes();

}
