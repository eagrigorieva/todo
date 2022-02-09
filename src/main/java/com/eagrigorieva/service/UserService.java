package com.eagrigorieva.service;

import com.eagrigorieva.exception.EntityNotFoundException;
import com.eagrigorieva.mapper.UserMapper;
import com.eagrigorieva.model.User;
import com.eagrigorieva.model.UserRole;
import com.eagrigorieva.storage.UserRepository;
import com.eagrigorieva.storage.UserRoleRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j2
@Getter
@Setter
@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public void create(String password, String username, String roleCode) {
        UserRole userRole = userRoleRepository.findByCode(roleCode);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userRole);
        userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.error("User not found");
            System.out.println("User not found");
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
        log.debug("User is deleted");
        System.out.println("SUCCESS");
    }
}
