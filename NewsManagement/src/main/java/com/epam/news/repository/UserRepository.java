package com.epam.news.repository;

import com.epam.news.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_READ_USER')")
    List<User> findAll();

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_USER')")
    User save(User user);

    // send next request
    // http://localhost:8899/api/users/search/findDistinctFirstByUsername?username=admin
    User findDistinctFirstByUsername(String username);
}