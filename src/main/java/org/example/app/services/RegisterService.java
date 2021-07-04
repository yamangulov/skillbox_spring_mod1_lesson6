package org.example.app.services;

import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andrey.Yamangulov
 * Date: 04.07.2021
 * Time: 8:06
 */
@Service
public class RegisterService {
    private final UserRepository userRepo;

    @Autowired
    public RegisterService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.retrieveAll();
    }

    public void registerUser(User user) {
        userRepo.store(user);
    }

    public boolean unregisterUserById(Integer id) {
        return userRepo.removeItemById(id);
    }
}
