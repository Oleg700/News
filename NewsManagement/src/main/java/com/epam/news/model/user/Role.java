package com.epam.news.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

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
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @Size(max = 40, message = "{validation.role.name.size}")
    @NotNull(message = "{validation.role.name.notNull}")
    private String name;

    /**
     * constructor.
     */
    public Role() {
    }

    /**
     * roles and privileges are connected with relation @ManyToMany.
     */
    @ManyToMany(fetch = FetchType.EAGER)
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

    public Role(final long id, final String name) {
        this.id = id;
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

    @Override
    public boolean equals(final Object object) {

        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Role role = (Role) object;
        return Objects.equals(id, role.id)
                && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, privileges);
    }
}
