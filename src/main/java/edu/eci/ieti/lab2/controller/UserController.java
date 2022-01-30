package edu.eci.ieti.lab2.controller;

import edu.eci.ieti.lab2.data.User;
import edu.eci.ieti.lab2.dto.UserDto;
import edu.eci.ieti.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {
    private final UserService userService;
    private final AtomicLong counter = new AtomicLong(0);

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        return new ResponseEntity<List<User>>(userService.getAll() , HttpStatus.OK );

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById(@PathVariable String id ) {


        return new ResponseEntity<User>(userService.findById(id) , HttpStatus.OK );
    }


    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto ) {
        User userCreation = new User((Integer.toString((int) counter.incrementAndGet())), userDto.getName() , userDto.getEmail() ,
                userDto.getLastName() , LocalDateTime.now().toString());
        return new ResponseEntity<User>(userService.create(userCreation), HttpStatus.OK );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id ) {
        User user = userService.findById(id);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        return new ResponseEntity<User>(userService.update(user , id ), HttpStatus.OK );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }
}
