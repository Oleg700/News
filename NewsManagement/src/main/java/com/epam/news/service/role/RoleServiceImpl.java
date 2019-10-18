package com.epam.news.service.role;

import com.epam.news.model.user.Role;
import com.epam.news.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * implementation of interface {@link RoleService}.
 *
 * @author Oleg Aliyev
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * privilegeRepository is used to process privilege objects in database.
     */
    private RoleRepository roleRepository;

    /**
     * constructor.
     *
     * @param roleRepository is used to process privilege objects in database.
     */
    @Autowired
    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role add(final Role role) {
        return roleRepository.save(role);
    }
}
