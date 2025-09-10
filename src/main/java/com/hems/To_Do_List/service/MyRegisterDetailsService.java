package com.hems.To_Do_List.service;

import com.hems.To_Do_List.model.Register;
import com.hems.To_Do_List.repo.RegisterRepo;
import com.hems.To_Do_List.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyRegisterDetailsService implements UserDetailsService {
    @Autowired
    private RegisterRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Register user = repo.findByUsername(username);
        if(user == null){
            System.out.println("User 4o4");
            throw new UsernameNotFoundException("User 4o4");
        }

        return new UserPrincipal(user);
    }
}