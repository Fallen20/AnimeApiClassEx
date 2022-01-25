package com.project.domain.model.projection;

import java.util.Set;

public interface ProjectionIndividualUserComments {
    String getUserid();
    String getUsername();

    Set<ProjectionIndividualUserAnimeComments> getUserComments();

}
