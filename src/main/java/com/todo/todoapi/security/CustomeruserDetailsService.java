package com.todo.todoapi.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.todoapi.enitity.Users;
import com.todo.todoapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomeruserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

         Users users=userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail).orElseThrow(()->new UsernameNotFoundException("user not exists by Username or Email"));

            Set<GrantedAuthority> authorities=users.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

            return new User(userNameOrEmail,null,authorities);
        }
    
}
