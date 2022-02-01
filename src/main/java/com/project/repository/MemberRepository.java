package com.project.repository;

import com.project.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    <T> List<T> findBy(Class<T> type);
}
