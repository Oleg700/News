package com.epam.news.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table
@Entity(name = "Privileges")
public class Privilege  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_seq")
    @SequenceGenerator(name = "privilege_seq", sequenceName = "privilege_seq", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    /*@ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;*/

    public Privilege() {
    }

    public Privilege(String name) {
        this.name = name;
    }

    public Privilege(long i, String name) {
        this.id = i;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }*/

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
