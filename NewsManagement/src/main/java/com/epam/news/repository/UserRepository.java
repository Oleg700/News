package com.epam.news.repository;

import com.epam.news.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findDistinctFirstByUsername(String username);
}