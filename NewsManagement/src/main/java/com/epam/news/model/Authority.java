package com.epam.news.model;

import javax.persistence.*;

@Table(name = "Authorities")
@Entity(name = "Authorities")
public class Authority {

    @Id
    @Column
    @SequenceGenerator(name = "auth_seq", sequenceName = "auth_seq", allocationSize = 1)
    private int id;


    @Column
    private String username;

    @Column
    private String authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
