package org.mshassium.pixonic.game.api.controller.game;

import org.mshassium.pixonic.game.api.db.model.User;
import org.mshassium.pixonic.game.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger("userController");

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        log.debug("Execute createUpdateUser");
        try {
            int userId = userService.createUser(user);
            return ResponseEntity.ok(userId);
        } catch (RuntimeException e) {
            log.error("User can not create because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        log.debug("Execute getAllUsers");
        try {
            List<User> allUser = userService.getAllUser();
            return ResponseEntity.ok(allUser);
        } catch (RuntimeException e) {
            log.error("Get All users failed because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/valentineGifts")
    public ResponseEntity getAllUsersWithValentineGifts() {
        log.debug("Execute getAllUsersWithValentineGifts");
        try {
            List<User> allUserWithGifts = userService.getAllUserWithValentineGift();
            return ResponseEntity.ok(allUserWithGifts);
        } catch (RuntimeException e) {
            log.error("Get All users with gifts failed because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody User user) {
        log.debug("Execute deleteUser");
        try {
            userService.deleteUser(user);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error("Delete User failed because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
