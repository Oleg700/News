package com.epam.news.service.privilege;

import com.epam.news.model.user.Privilege;
import com.epam.news.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * implementation of interface {@link PrivilegeService}.
 *
 * @author Oleg Aliyev
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    /**
     * privilegeDao is used to process privilege objects in database.
     */
    private PrivilegeRepository privilegeRepository;

    /**
     * constructor.
     *
     * @param privilegeRepository is used to process privilege objects in database.
     */
    @Autowired
    public PrivilegeServiceImpl(final PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Collection<Privilege> getAll() {
        return privilegeRepository.findAll();
    }


    @Override
    @Transactional
    public Privilege add(final Privilege privilege) {
        return privilegeRepository.save(privilege);
    }
}
