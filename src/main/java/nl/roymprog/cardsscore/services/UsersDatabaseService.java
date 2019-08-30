package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersDatabaseService {

    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public Optional<User> getUser(UUID id) {
        List<User> userList = getUserList();

        return userList.stream().parallel()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    public User insertUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot add null user");
        }

        userList.add(user);

        return user;
    }
}
