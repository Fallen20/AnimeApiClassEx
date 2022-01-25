package com.project.domain.model.projection;

import java.util.Set;
import java.util.UUID;

public interface ProjectionIndividualUserAnimeComments {

   UUID getCommentid();
   String getComentario();
   Set<ProjectionAnimeName> getCommentedAnime();

}
