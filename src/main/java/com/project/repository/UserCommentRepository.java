package com.project.repository;

import com.project.domain.model.CommentUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCommentRepository extends JpaRepository<CommentUser, UUID> {
}
