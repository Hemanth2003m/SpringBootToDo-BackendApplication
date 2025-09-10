package com.hems.To_Do_List.service;

import com.hems.To_Do_List.model.Register;
import com.hems.To_Do_List.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepo registerRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Register addUser(Register user){
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return registerRepo.save(user);
    }


    public List<Register> getUsers() {
       return registerRepo.findAll();
    }
}
