package com.SwordboundSouls.service;

import com.SwordboundSouls.entity.Role;
import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null)
            throw new UsernameNotFoundException("ERROR: User " + username + " not found");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                rolesToGrantedAuthorities(user.getUserRoles())
        );
    }

    private List<GrantedAuthority> rolesToGrantedAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role : roles)
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void registerNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("USER"));
        roles.add(roleService.findByName("ADMIN"));

        user.setUserRoles(roles);

        userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getCurrentAuthenticatedUser(){
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

    //TODO: Improve this
    public boolean usernameIsNotValid(String username) {
        return username.isEmpty();
    }

    //TODO: Improve this
    public boolean passwordIsNotValid(String password){
        return password.isEmpty();
    }
}
