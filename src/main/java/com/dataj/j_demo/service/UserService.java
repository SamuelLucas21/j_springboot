package com.dataj.j_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dataj.j_demo.model.UserEntidenty;
import com.dataj.j_demo.repository.UserRepository;

import jakarta.validation.Valid;

@Service
@RestController
@RequestMapping("/api/users")
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository){
        this.userRepository = repository;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete_user(
        @PathVariable("id")
        Integer id_user){

        userRepository.findById(id_user)
            .map(t ->{
                this.userRepository.delete(t);
                return Void.TYPE;
            })
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    
    }

    @GetMapping
    public List<?> get_users(){
        return this.userRepository.findAll();
    }

    @GetMapping("{id}")
    public UserEntidenty get_user(@PathVariable("id") 
                                    Integer id){

        return this.userRepository
                   .findById(id)
                   .orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Usuário não identificado")
                   );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update_User(
        @PathVariable("id") Integer id,
        @Valid 
        @RequestBody
        UserEntidenty newUser
    ){

        userRepository.findById(id).map(t ->{
            newUser.setId(id);            
            newUser.setData_born(t.getData_born());
            newUser.setCpf(t.getCpf());

            return userRepository.save(newUser);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserEntidenty set_user(@RequestBody @Valid UserEntidenty user){
        return this.userRepository.save(user);
    }
}
