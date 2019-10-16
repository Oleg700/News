package com.epam.news.repository;

import com.epam.news.model.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Override
    List<Privilege> findAll();

    @Override
    Privilege save(Privilege news);
}
