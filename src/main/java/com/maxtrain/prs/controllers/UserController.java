package com.maxtrain.prs.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.maxtrain.prs.models.JsonResponse;
import com.maxtrain.prs.models.User;
import com.maxtrain.prs.repositories.UserRepository;

@CrossOrigin
@Controller
@RequestMapping(path="/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("{username}/{password}")
    public ResponseEntity<User> getForLogin(@PathVariable String username, @PathVariable String password) {
    	Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
    	return user.isPresent() 
    			? new ResponseEntity<User>(user.get(), HttpStatus.OK)
    			: new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(path="/ListRaw")
    public @ResponseBody Iterable<User> getAllUsersRaw() {
    	return userRepository.findAll();
    }
    @GetMapping(path="/List")
    public @ResponseBody JsonResponse getAllUsers() {
        return new JsonResponse(userRepository.findAll());
    }

    @GetMapping(path="/Get/{id}")
    public @ResponseBody JsonResponse getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return new JsonResponse(user.isPresent() ? user.get() : null);
    }

    private @ResponseBody JsonResponse saveUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return new JsonResponse(0, "User Added/Changed.", user, null);
        } catch (DataIntegrityViolationException ex) {
            return new JsonResponse(-1, ex.getRootCause().toString(), null, ex);
        } catch (Exception ex) {
            return new JsonResponse(-2, ex.getMessage(), null, ex);
        }
    }

    @PostMapping("/Create")
    public @ResponseBody JsonResponse addUser(@RequestBody User user) {
        return saveUser(user);
    }
    @PostMapping("/Change")
    public @ResponseBody JsonResponse changeUser(@RequestBody User user) {
        return saveUser(user);
    }
    @PostMapping("/Remove")
    public @ResponseBody JsonResponse removeUser(@RequestBody User user) {
        try {
            userRepository.delete(user);
            return new JsonResponse(0, "User Deleted.", user, null);
        } catch (Exception ex) {
            return new JsonResponse(-2, ex.getMessage(), null, ex);
        }
    }
}
