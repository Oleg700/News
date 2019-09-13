package com.epam.news.security;

import com.epam.news.model.user.Privilege;
import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import com.epam.news.model.user.MyUserPrincipal;
import com.epam.news.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserService implements UserDetailsService {


    private UserService userService;

    public CustomUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = this.userService.getByName(username);
        Collection roles = user.getRoles();
        Collection<Privilege> privileges = getPrivileges(roles);
        return new MyUserPrincipal(user);
    }

    private Collection<Privilege> getPrivileges(Collection<Role> roles) {
        Collection<Privilege> privileges = new HashSet();
        for (Role role : roles) {
            Collection<Privilege> temp = role.getPrivileges();
            for (Privilege privilege : temp) {
                privileges.add(privilege);
            }
        }
        return privileges;
    }
}
