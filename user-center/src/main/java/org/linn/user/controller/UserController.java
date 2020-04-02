package org.linn.user.controller;

import lombok.RequiredArgsConstructor;
import org.linn.user.entity.User;
import org.linn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * (User)表控制层
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Optional<?> finById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

}