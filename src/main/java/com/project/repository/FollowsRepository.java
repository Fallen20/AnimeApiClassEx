package com.project.repository;

import com.project.domain.model.FollowsUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FollowsRepository extends JpaRepository<FollowsUsers, UUID> {

    List<FollowsUsers> findBy();


    //<T> T findByUserid(UUID userid, Class<T> type);
}
