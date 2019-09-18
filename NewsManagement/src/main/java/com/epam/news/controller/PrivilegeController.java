package com.epam.news.controller;

import com.epam.news.model.user.Privilege;
import com.epam.news.service.privilege.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;


    @PreAuthorize("hasAuthority('PRIVILEGE_READ_PRIVILEGE')")
    @GetMapping(value = "/privileges")
    public ResponseEntity<List<Privilege>> getAllPrivileges() {
        List<Privilege> listPrivileges = (List<Privilege>) privilegeService.getAll();
        return ResponseEntity.ok().body(listPrivileges);
    }


    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_PRIVILEGE')")
    @PostMapping(value = "/privileges")
    public ResponseEntity<Privilege> addPrivilege(@RequestBody Privilege privilege) {
        Privilege savedPrivilege = privilegeService.add(privilege);
        return ResponseEntity.ok().body(savedPrivilege);
    }
}
