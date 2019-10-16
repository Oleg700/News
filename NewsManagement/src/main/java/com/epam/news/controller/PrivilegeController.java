/*
package com.epam.news.controller;

import com.epam.news.model.user.Privilege;
import com.epam.news.service.privilege.PrivilegeService;
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

*/
/**
 * REST API controller for processing queries with entity {@link Privilege}.
 *
 * @author Oleg Aliyev
 *//*

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class PrivilegeController {

    */
/**
     * service is used to interact with database.
     *//*

    @Autowired
    private PrivilegeService privilegeService;

    */
/**
     * returns list of privileges
     * only users with role admin has access to this information.
     *
     * @return list of privileges
     *//*

    @PreAuthorize("hasAuthority('PRIVILEGE_READ_PRIVILEGE')")
    @GetMapping(value = "/privileges")
    public ResponseEntity<List<Privilege>> getAllPrivileges() {
        List<Privilege> listPrivileges = (List<Privilege>)
                privilegeService.getAll();
        return ResponseEntity.ok().body(listPrivileges);
    }

    */
/**
     * request to save privilege to database.
     *
     * @param privilege to save
     * @return privilege
     *//*

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_PRIVILEGE')")
    @PostMapping(value = "/privileges")
    public ResponseEntity<Privilege> addPrivilege(
            @RequestBody final @Valid Privilege privilege) {
        Privilege savedPrivilege = privilegeService.add(privilege);
        return ResponseEntity.ok().body(savedPrivilege);
    }
}
*/
