package com.project.domain.model.projection;

import java.util.Set;

public interface ProjectionUserComments {
    String getUserid();
    String getUsername();

    Set<ProjectionAnimeComments> getUserComments();

}
