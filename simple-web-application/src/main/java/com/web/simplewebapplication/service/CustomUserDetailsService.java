package com.web.simplewebapplication.service;

import com.web.simplewebapplication.models.Privilege;
import com.web.simplewebapplication.models.Role;
import com.web.simplewebapplication.models.User;
import com.web.simplewebapplication.repository.RoleRepository;
import com.web.simplewebapplication.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private AuthService authService;
    private MessageSource messageSource;
    private RoleRepository roleRepository;

    public CustomUserDetailsService() {

    }

    public CustomUserDetailsService(UserRepository userRepository, AuthService authService, MessageSource messageSource, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.messageSource = messageSource;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);
        if(user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true,
                    true, true,
                    getAuthorities(Arrays.asList(roleRepository.getByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                true, true,
                true, true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();

        for(Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }

        for(Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }

        return authorities;
    }
}
