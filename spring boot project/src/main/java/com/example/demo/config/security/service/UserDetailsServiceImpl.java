package com.example.demo.config.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StpUser;
import com.example.demo.repository.StpUserRepository;
import com.example.demo.utils.Constant;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    StpUserRepository stpUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StpUser user = stpUserRepository.findByUsernameAndStatusFlag(username, Constant.StatusFlag.ACTIVE.code()).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}

        
		// List<UserRole> userMappingRoleList = new ArrayList<>();
		// UserRole userRole = new UserRole();
		// List<StpEmployee> emp = employeeRepository.findByUserRegisterId(user.getId());
		// emp.stream().map(o->o.get)
		// userRole.setRoleName("ROLE_USER");
		// userMappingRoleList.add(userRole);
		return (UserDetails) UserDetailsImpl.build(user);
    }

}
