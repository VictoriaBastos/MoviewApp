package com.bastos.moview.controller.user;

import com.bastos.moview.domain.User;
import com.bastos.moview.repositories.UserRepository;
import com.bastos.moview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Optional<User>> postUsuario(@RequestBody User user) {
        Optional<User> novoUsuario = userService.registerUser(user);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logar")
    public ResponseEntity<UserLogin> autenticationUsuario(@RequestBody UserLogin userLogin) {
        try {
            Optional<UserLogin> user = userService.login(userLogin);
            return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<User> putUser(@RequestBody User user) {
        Optional<User> updateUser = userService.updateUser(user);
        if (updateUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updateUser.get());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public void deleteUsuario(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usu??rio n??o encontrado!", null);
        }
    }

    @PutMapping("/cadastrar-moderador/{id}")
    public ResponseEntity<CreateModeratorResponse> putCreateModerator(@PathVariable int id) {

        CreateModeratorResponse response = userService.createModerator(id);
        return ResponseEntity.status(201).body(response);
    }
}

