package vn.com.rfim_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.rfim_api.services.UserService;
import vn.com.rfim_api.services.dtos.UserDTO;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody UserDTO request) {
        return service.login(request.getUsername(), request.getPassword());
//        return ResponseEntity.ok(request.getUsername() + request.getPassword());
    }
}
