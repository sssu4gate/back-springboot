package com.gate.planner.gate.dao.user;

import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickName(String nickName);

    Optional<User> findByNickName(String nickName);
}
