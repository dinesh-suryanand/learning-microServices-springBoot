package in.dinesh.restful.restfulservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

//    constructor based
//    private UserDaoService userDaoService;
//    public UserResource(UserDaoService userDaoService){
//        this.userDaoService = userDaoService;
//    }

    @GetMapping("/users")
    public List<User> retriveUsers(){
        return userDaoService.findAll();
    }


    //changing this method to send links , using hateoas
    // creating link to all users

    //EntityModel , WebMvcLinkBuilder
    @GetMapping("/users/{userId}")
    public EntityModel<User> retriveUser(@PathVariable long userId){
        User user = userDaoService.findUser(userId);
        if(user == null)
            throw  new UserNotFoundException("id:" + userId);

        EntityModel<User> entityModel = EntityModel.of(user);

        //return link is to the retive users i.e the above method
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveUsers());

        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{userId}")
    public void  deleteUser(@PathVariable long userId){
        userDaoService.deleteById(userId);
    }
}
