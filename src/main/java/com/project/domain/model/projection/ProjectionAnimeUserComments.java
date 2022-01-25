package com.project.domain.model.projection;

import java.util.Set;

public interface ProjectionAnimeUserComments {
    String getName();//nombre anime
    Set<ProjectionAnimeUserComments2> getComments();

}
