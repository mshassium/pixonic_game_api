package org.mshassium.pixonic.game.api.service;

import org.mshassium.pixonic.game.api.db.model.User;
import org.mshassium.pixonic.game.api.db.repository.CrudUserRepository;
import org.mshassium.pixonic.game.api.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger("UserService");
    private final UserRepository userRepository;
    private final CrudUserRepository crudUserRepository;

    public UserService(UserRepository userRepository, CrudUserRepository crudUserRepository) {
        this.userRepository = userRepository;
        this.crudUserRepository = crudUserRepository;
    }

    public int createUser(User newUser) {
        log.debug("Execute createUser method");

        return userRepository.saveShardingUser(newUser);
    }

    public User updateUser(User newUser) {
        log.debug("Execute createUpdateUser method");

        return crudUserRepository.save(newUser);
    }

    public void deleteUser(User user) {
        log.debug("Execute deleteUser");
        crudUserRepository.delete(user);
    }

    public List<User> getAllUser() {
        log.debug("Execute getAllUser");
        return (List<User>) crudUserRepository.findAll();
    }

    public List<User> getAllUserWithValentineGift() {
        log.debug("Execute getAllUserWithValentineGift");
        return StreamSupport.stream(crudUserRepository.findAll().spliterator(), false)
                .filter(User::haveGift)
                .collect(Collectors.toList());
    }

    public User getUserById(String id) {
        log.debug("Execute getUserById");
        Optional<User> userById = crudUserRepository.findById(id);
        if (userById.isPresent()) {
            return userById.get();
        } else {
            String errorMessage = "User with id:" + id + " not found";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }


}
