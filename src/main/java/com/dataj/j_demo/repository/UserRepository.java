package com.dataj.j_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dataj.j_demo.model.UserEntidenty;

public interface UserRepository 
        extends JpaRepository<UserEntidenty,Integer> {

}
