package com.epam.news.model.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class is used for Spring Security authorization and authentication.
 *
 * <p>
 * Overrides methods of inteface UserDetails
 * for authorization in {@link com.epam.news.security.CustomUserService}.
 * <p>
 *
 * @author Oleg Aliyev
 * @see com.epam.news.security.CustomUserService
 * @see org.springframework.security.core.userdetails.UserDetails
 */
public class UserPrincipal implements UserDetails {

    /**
     * UserPrincipal is used as wrapper for user class.
     */
    private User user;

    /**
     * constructor.
     *
     * @param user UserPrincipal is used as wrapper for user class
     */
    public UserPrincipal(final User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        }
        authorities.stream().forEach(System.out::println);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
