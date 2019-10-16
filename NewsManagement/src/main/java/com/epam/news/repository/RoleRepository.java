package com.epam.news.repository;

import com.epam.news.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    List<Role> findAll();

    @Override
    Role save(Role role);

}

