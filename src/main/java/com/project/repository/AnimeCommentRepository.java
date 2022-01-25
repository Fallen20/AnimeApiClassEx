package com.project.repository;

import com.project.domain.model.CommentAnime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimeCommentRepository extends JpaRepository<CommentAnime, UUID> {
}
