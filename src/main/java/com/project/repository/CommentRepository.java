package com.project.repository;

import com.project.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Comment findByCommentid(UUID commentid);

}
