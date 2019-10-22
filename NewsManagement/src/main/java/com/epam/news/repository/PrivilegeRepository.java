package com.epam.news.repository;

import com.epam.news.model.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_READ_PRIVILEGE')")
    List<Privilege> findAll();

    @Override
    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_PRIVILEGE')")
    Privilege save(Privilege privilege);
}
