package com.hrd.springsecurity.service;

import com.hrd.springsecurity.repository.UserDetailsRepo;
import com.hrd.springsecurity.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(userDetailsRepo.loadUserByUsername(s));
        User user = userDetailsRepo.loadUserByUsername(s);
        return user == null ? new User() : user;
    }
}
