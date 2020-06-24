package pgt.goal.controllers;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pgt.goal.models.User;
import pgt.goal.repository.UserRepository;


@Slf4j
@RestController
public class Controller {

    @Autowired
    private UserRepository repository;

    @GetMapping("/")
    public String helloWorld() {
        log.info("Called HelloWorld");
        return "Hello World";
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            allUsers = repository.find(null);
        } catch (MongoException exception) {
            log.error("Unable to find all users: {}", exception.getMessage());
            log.error("{}", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<User>> getUserById(@PathVariable String id) {
        List<User> users = new ArrayList<>();
        try {
            users = repository.find(id);
        } catch (MongoException ex) {
            log.error("Unable to find user with id: {} error: {}", id, ex.getMessage());
            log.error("{}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping(value="/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String createdUserId = "";
        try {
        createdUserId = repository.insertUser(user);
        } catch (Exception ex) {
            log.error("Unable to insert user: {}", user.getName(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return ResponseEntity.ok(createdUserId);
    }
    
    @GetMapping(value="/home")
    public String home() {
        return "This is home";
    }
}