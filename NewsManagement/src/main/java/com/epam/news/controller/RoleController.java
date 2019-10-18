package com.epam.news.controller;

import com.epam.news.model.user.Role;
import com.epam.news.repository.RoleRepository;
import com.epam.news.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * REST API controller for processing queries with entity {@link Role}.
 *
 * @author Oleg Aliyev
 * <p>
 * service is used to interact with database.
 * <p>
 * returns list of roles
 * only users with role admin has access to this information.
 * @return list of roles
 * <p>
 * request to save role to database.
 * @param role to save
 * @return role
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class RoleController {

/**
 * service is used to interact with database.
 */

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;
/**
 * returns list of roles
 * only users with role admin has access to this information.
 *
 * @return list of roles
 */

    @PreAuthorize("hasAuthority('PRIVILEGE_READ_ROLE')")
    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> listRoles = (List<Role>) roleService.getAll();
        return ResponseEntity.ok().body(listRoles);
    }

/**
 * request to save role to database.
 *
 * @param role to save
 * @return role
 */

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_ROLE')")
    @PostMapping(value = "/roles")
    public ResponseEntity<Role> addRole(@RequestBody @Valid final Role role) {
        Role role1 = roleRepository.save(role);
        return ResponseEntity.ok().body(role1);
    }
}
