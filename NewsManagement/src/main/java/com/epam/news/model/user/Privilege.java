package com.epam.news.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Every role {@link Role} has a set of restricted privileges.
 *
 * <p>
 * Privileges are established as restriction in controllers for every request
 * <p>
 *
 * @author Oleg Aliyev
 * @see com.epam.news.controller.NewsController
 */
@Table
@Entity(name = "Privileges")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({
        @NamedQuery(
                name = "getAllPrivileges",
                query = "select p from Privileges p"
        )
})
public class Privilege implements GrantedAuthority {

    /**
     * privilege id is generated with sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "privilege_seq")
    @SequenceGenerator(name = "privilege_seq",
            sequenceName = "privilege_seq", allocationSize = 1)
    private Long id;

    /**
     * privilege name.
     */
    @Column
    @Size( max=100, message="name must be less than 100 characters")
    @NotNull(message = "Please provide a name")
    private String name;

    public Privilege() {
    }

    public Privilege(final String name) {
        this.name = name;
    }

    public Privilege(final long i, final String name) {
        this.id = i;
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
        Privilege privilege = (Privilege) object;
        return Objects.equals(id, privilege.id)
                && Objects.equals(name, privilege.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}


