package com.epam.news.model.user;


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
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Class is used for Spring Security authorization.
 *
 * @author Oleg Aliyev
 * @see UserPrincipal
 * @see org.springframework.security.core.userdetails.UserDetails
 */
@Entity(name = "Users")
@Table
@NamedQueries({
        @NamedQuery(
                name = "getAllUsers",
                query = "select u from Users u"
        ),
        @NamedQuery(
                name = "getUserByName",
                query = "select c from Users c where c.username = :name"
        )
}
)
public class User implements Serializable {

    /**
     * userId is generated with sequence in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq", allocationSize = 1)
    private Long id;



    /**
     * username.
     */
    @Column(name = "username")
    private String username;

    /**
     * user password.
     */
    @Column
    private String password;

    /**
     * enabled, checks current user status.
     */
    @Column
    private boolean enabled;

    /**
     * list of roles.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(final Long id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(final String username,
                final String password, final Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", username='"
                + username
                + '\''
                + ", password='"
                + password
                + '\''
                + ", enabled="
                + enabled
                + ", roles="
                + roles
                + '}';
    }

    @Override
    public boolean equals(final Object object) {

        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        User user = (User) object;
        return enabled == user.enabled
                && Objects.equals(id, user.id)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, enabled);
    }
}
