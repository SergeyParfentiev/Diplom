package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.tables.CustomUser;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService managerService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CustomUser user = managerService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login + " not correct login or password");
        }

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new User(user.getLogin(), user.getPassword(), roles);
    }
}
