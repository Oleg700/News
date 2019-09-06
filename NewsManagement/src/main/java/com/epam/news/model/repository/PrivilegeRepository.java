package com.epam.news.model.repository;

import com.epam.news.model.News;
import com.epam.news.model.Privilege;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

public class PrivilegeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Privilege findByName(String name){
        return (Privilege) entityManager.createQuery("select c from Privileges c where c.name = :name").setParameter("name",name).getSingleResult();
    }



    @Transactional
    public Privilege save(Privilege privilege){
        entityManager.merge(privilege);
    /*    News news = new News(1,"New-Jersey", LocalDateTime.now(), "brief", "content");
        entityManager.merge(news);
        entityManager.close();*/

        return privilege;
    }


}
