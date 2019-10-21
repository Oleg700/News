package com.epam.news.repository;

import com.epam.news.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_READ_ROLE')")
    List<Role> findAll();

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_ROLE')")
    Role save(Role role);

}

