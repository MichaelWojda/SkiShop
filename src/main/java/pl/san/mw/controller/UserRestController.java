package pl.san.mw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.san.mw.model.AppUser;
import pl.san.mw.repositories.UserRepository;
import pl.san.mw.service.UserService;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/users/")
public class UserRestController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUser>> getAll() {
        List<AppUser> list = userRepository.findAll();

        return ResponseEntity.ok(list);


    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> getOne(@PathVariable Long id) {
        AppUser user = userRepository.findById(id).orElse(new AppUser());
        return ResponseEntity.ok(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser){
        AppUser savedUser = userService.createSimpleUser(appUser);
        if(appUser.getId()==null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
            return ResponseEntity.created(location).body(savedUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}