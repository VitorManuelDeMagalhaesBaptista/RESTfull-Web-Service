package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    //retrieve the data of all the users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //retrieve data of a specific user
    //hateoas - meter links na data de um user
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        //criar uma exception para quando introduzirmos valores errados ou qdo o user não existe: ex users/101
        //neste caso criamos uma classe que extende de runtimeexceptions
        if(user == null){
            throw new UserNotFoundException("id:" + id);
        }

       EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //builder que facilita construir links a apontar para controlers de spring mvc
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    //delete data of a specific user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }

    //Post all the details of users
    //valid ajuda a que não se possa meter dados parvos, como nome vazio, DoB do futuro, etc
    @PostMapping("/users")
    public ResponseEntity<User> createUser( @Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
