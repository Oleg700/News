package com.epam.news.util;

import com.epam.news.model.user.Privilege;
import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DatabaseInitialization {

    private static final String ADMIN_PASSWORD = "admin";
    private static final String EDITOR_PASSWORD = "editor";
    private static final String USER_PASSWORD = "user";

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_EDITOR = "ROLE_EDITOR";
    private static final String ROLE_USER = "ROLE_USER";

    private static final String PRIVILEGE_WRITE_COMMENT = "PRIVILEGE_WRITE_COMMENT";
    private static final String PRIVILEGE_WRITE_NEWS = "PRIVILEGE_WRITE_NEWS";
    private static final String PRIVILEGE_UPDATE_NEWS = "PRIVILEGE_UPDATE_NEWS";
    private static final String PRIVILEGE_DELETE_NEWS = "PRIVILEGE_DELETE_NEWS";
    private static final String PRIVILEGE_READ_PRIVILEGE = "PRIVILEGE_READ_PRIVILEGE";
    private static final String PRIVILEGE_WRITE_PRIVILEGE = "PRIVILEGE_WRITE_PRIVILEGE";
    private static final String PRIVILEGE_READ_ROLE = "PRIVILEGE_READ_ROLE";
    private static final String PRIVILEGE_WRITE_ROLE = "PRIVILEGE_WRITE_ROLE";
    private static final String PRIVILEGE_READ_USER = "PRIVILEGE_READ_USER";
    private static final String PRIVILEGE_WRITE_USER = "PRIVILEGE_WRITE_USER";


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {

    }

    public void initializeDatabase(){

        String adminPassword = passwordEncoder.encode(ADMIN_PASSWORD);
        String editorPassword = passwordEncoder.encode(EDITOR_PASSWORD);
        String userPassword = passwordEncoder.encode(USER_PASSWORD);

        User admin = new User("admin", ADMIN_PASSWORD);
        Role roleAdmin = new Role("ROLE_ADMIN");



        Privilege privilegeWriteComment = entityManager.createQuery("select p from privilege where name = PRIVILEGE_WRITE_COMMENT", Privilege.class).getSingleResult();
        Privilege privilegeWriteNews = new Privilege("PRIVILEGE_WRITE_NEWS");
        Privilege privilegeUpdateNews = new Privilege("PRIVILEGE_UPDATE_NEWS");
        Privilege privilegeDeleteNews = new Privilege("PRIVILEGE_DELETE_NEWS");
        Privilege privilegeReadPrivilege = new Privilege("PRIVILEGE_READ_PRIVILEGE");
        Privilege privilegeWritePrivilege = new Privilege("PRIVILEGE_WRITE_PRIVILEGE");
        Privilege privilegeReadRole= new Privilege("PRIVILEGE_READ_ROLE");
        Privilege privilegeWriteRole = new Privilege("PRIVILEGE_WRITE_ROLE");
        Privilege privilegeReadUser = new Privilege("PRIVILEGE_READ_USER");
        Privilege privilegeWriteUser = new Privilege("PRIVILEGE_WRITE_USER");







        Collection<Privilege> privilegesAdmin = new ArrayList();
        privilegesAdmin.add(privilegeWriteComment);
        privilegesAdmin.add(privilegeReadPrivilege);
        privilegesAdmin.add(privilegeWritePrivilege);
        privilegesAdmin.add(privilegeReadRole);
        privilegesAdmin.add(privilegeWriteRole);
        privilegesAdmin.add(privilegeReadUser);
        privilegesAdmin.add(privilegeWriteUser);
        roleAdmin.setPrivileges(privilegesAdmin);
        admin.setRoles(Collections.singleton(roleAdmin));

        entityManager.merge(privilegeWriteUser);
        entityManager.merge(roleAdmin);
        entityManager.merge(admin);
    }

    public void printPrivilege(){
        Privilege privilegeWriteComment = entityManager.createQuery("select p from Privileges p where p.name = 'PRIVILEGE_WRITE_COMMENT'", Privilege.class).getSingleResult();
        System.out.println(privilegeWriteComment.getName());
    }

    public void printPrivileges(){
        List<Privilege> privilegelist= entityManager.createQuery("select p from Privileges p ", Privilege.class).getResultList();
        privilegelist.stream().forEach(p -> System.out.println(p.getName()));
    }

    @Transactional
    public void deleteFromPrivileges() {
        Query q3=  entityManager.
                createQuery("delete from Roles ");
        q3.executeUpdate();
    }

    @Transactional
    public void addRoles() {
        Privilege privilegeWriteComment = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_WRITE_COMMENT'", Privilege.class).getSingleResult();

        Privilege privilegeWriteNews = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_WRITE_NEWS'", Privilege.class).getSingleResult();

        Privilege privilegeUpdateNews = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_UPDATE_NEWS'", Privilege.class).getSingleResult();

        Privilege privilegeDeleteNews = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_DELETE_NEWS'", Privilege.class).getSingleResult();

        Collection<Privilege> privilegesEditor = new ArrayList();
        privilegesEditor.add(privilegeWriteComment);
        privilegesEditor.add(privilegeWriteNews);
        privilegesEditor.add(privilegeUpdateNews);
        privilegesEditor.add(privilegeDeleteNews);
        Role roleEditor = new Role(ROLE_EDITOR);
        roleEditor.setPrivileges(privilegesEditor);
        entityManager.merge(roleEditor);



        Privilege privilegeReadPrivilege = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_READ_PRIVILEGE'", Privilege.class).getSingleResult();

        Privilege privilegeWritePrivilege = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_WRITE_PRIVILEGE'", Privilege.class).getSingleResult();

        Privilege privilegeReadRole = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_READ_ROLE'", Privilege.class).getSingleResult();

        Privilege privilegeWriteRole = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_WRITE_ROLE'", Privilege.class).getSingleResult();

        Privilege privilegeReadUser = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_READ_USER'", Privilege.class).getSingleResult();

        Privilege privilegeWriteUser = entityManager.
                createQuery("select p from Privileges p where p.name = 'PRIVILEGE_WRITE_USER'", Privilege.class).getSingleResult();

        Collection<Privilege> privilegesAdmin = new ArrayList();
        privilegesAdmin.add(privilegeReadPrivilege);
        privilegesAdmin.add(privilegeWritePrivilege);
        privilegesAdmin.add(privilegeReadRole);
        privilegesAdmin.add(privilegeWriteRole);
        privilegesAdmin.add(privilegeReadUser);
        privilegesAdmin.add(privilegeWriteUser);
        Role roleAdmin = new Role(ROLE_ADMIN);
        roleAdmin.setPrivileges(privilegesAdmin);
        entityManager.merge(roleAdmin);


        Role roleUser = new Role(ROLE_USER);
        roleUser.setPrivileges(Collections.singleton(privilegeWriteComment));
        entityManager.merge(roleUser);
    }


   @Transactional
   public void createPrivileges(){
       Privilege privilegeWriteComment = new Privilege(PRIVILEGE_WRITE_COMMENT);
       Privilege privilegeWriteNews = new Privilege(PRIVILEGE_WRITE_NEWS);
       Privilege privilegeUpdateNews = new Privilege(PRIVILEGE_UPDATE_NEWS);
       Privilege privilegeDeleteNews = new Privilege(PRIVILEGE_DELETE_NEWS);
       Privilege privilegeReadPrivilege = new Privilege(PRIVILEGE_READ_PRIVILEGE);
       Privilege privilegeWritePrivilege = new Privilege(PRIVILEGE_WRITE_PRIVILEGE);
       Privilege privilegeReadRole= new Privilege(PRIVILEGE_READ_ROLE);
       Privilege privilegeWriteRole = new Privilege(PRIVILEGE_WRITE_ROLE);
       Privilege privilegeReadUser = new Privilege(PRIVILEGE_READ_USER);
       Privilege privilegeWriteUser = new Privilege(PRIVILEGE_WRITE_USER);

       entityManager.merge(privilegeWriteComment);
       entityManager.merge(privilegeWriteNews);
       entityManager.merge(privilegeUpdateNews);
       entityManager.merge(privilegeDeleteNews);
       entityManager.merge(privilegeReadPrivilege);
       entityManager.merge(privilegeWritePrivilege);
       entityManager.merge(privilegeReadRole);
       entityManager.merge(privilegeWriteRole);
       entityManager.merge(privilegeReadUser);
       entityManager.merge(privilegeWriteUser);
   }


}
