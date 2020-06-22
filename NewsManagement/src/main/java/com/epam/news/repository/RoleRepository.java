package com.epam.news.repository;

import com.epam.news.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_READ_ROLE')")
    List<Role> findAll();

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_ROLE')")
    Role save(@Valid Role role);

}

