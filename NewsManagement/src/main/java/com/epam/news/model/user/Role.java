package com.epam.news.model.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

/**
 * Every user {@link User} can have many roles.
 *
 * <p>
 * Roles are connected with {@link User} in @ManyToMany
 * <p>
 *
 * @author Oleg Aliyev
 */
@Entity(name = "Roles")
@Table
@NamedQueries(
        @NamedQuery(
                name = "getAllRoles",
                query = "select r from Roles r"
        )
)
public class Role implements GrantedAuthority {

    /**
     * role id is generated with sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq",
            sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    /**
     * role name.
     */
    @Column
    private String name;

    /**
     * constructor.
     */
    public Role() {
    }

    /**
     * roles and privileges are connected with relation @ManyToMany.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;


    public Role(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
