package com.epam.news.repository;

import com.epam.news.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "news", path = "news")
public interface NewsRepository extends JpaRepository<News, Long> {


    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_NEWS')")
    News save(News news);

    @PreAuthorize("hasAuthority('PRIVILEGE_DELETE_NEWS')")
    void deleteById(final Long id);

}
