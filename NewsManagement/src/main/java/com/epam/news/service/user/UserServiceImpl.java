package com.epam.news.service.user;

import com.epam.news.dao.user.UserDao;
import com.epam.news.model.Role;
import com.epam.news.model.User;
import com.epam.news.service.privilege.PrivilegeService;
import com.epam.news.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{

    private static final String USER_ROLE = "ROLE_USER";
    private static final String USER_PRIVILEGE = "READ_PRIVILEGE";

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll(){return userDao.getAll();}

    @Override
    public User getByName(String name){
        return userDao.getByName(name);
    }

    @Override
    @Transactional
    public User add(User user) {
        String hashPassword = encodePassword(user.getPassword());
        user.setPassword(hashPassword);
        user.setRoles(createUserRole());
        return userDao.add(user);
    }


    private String encodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);
        return hash;
    }

    private ArrayList<Role> createUserRole(){
        ArrayList<Role> listRoles= new ArrayList<>();
        Role roleUser = roleService.getByName(USER_ROLE);
        listRoles.add(roleUser);
        return listRoles;
    }
}
