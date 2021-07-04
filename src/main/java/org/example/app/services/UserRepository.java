package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.User;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey.Yamangulov
 * Date: 04.07.2021
 * Time: 8:03
 */
@Repository
public class UserRepository implements ProjectRepository<User> {

    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final List<User> userRepo = new ArrayList<>();

    @Override
    public List<User> retrieveAll() {
        return new ArrayList<>(userRepo);
    }

    @Override
    public void store(User user) {
        for (User existingUser : retrieveAll()) {
            if (existingUser.getUser().equals(user.getUser())) return;
        }
        if (!user.getUser().isEmpty() && !user.getPassword().isEmpty()) {
            user.setId(user.hashCode());
            logger.info("added new user: " + user);
            userRepo.add(user);
        }
    }

    @Override
    public boolean removeItemById(Integer userIdToRemove) {
        for (User user : retrieveAll()) {
            if (user.getId().equals(userIdToRemove)) {
                logger.info("user " + user + " unregistered");
                return userRepo.remove(user);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String author) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeItemByTitle(String title) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeItemBySize(Integer size) {
        throw new NotImplementedException();
    }
}
