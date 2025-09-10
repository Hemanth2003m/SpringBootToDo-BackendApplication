package com.hems.To_Do_List.repo;

import com.hems.To_Do_List.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Long> {

    Register findByUsername(String username);

}
