package com.project.repository;

import com.project.domain.model.Grupos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Grupos, UUID> {
    <T> List<T> findBy(Class<T> type);

    <T> List<T> findByGroupid(UUID id,Class<T> type);
    Grupos findByGroupid(UUID groupid);

}
