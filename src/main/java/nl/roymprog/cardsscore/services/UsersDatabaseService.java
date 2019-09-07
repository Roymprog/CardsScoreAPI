package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersDatabaseService {

    private List<UserResponse> UserResponseList = new ArrayList<>();

    public List<UserResponse> getUserResponseList() {
        return UserResponseList;
    }

    public Optional<UserResponse> getUser(String id) {
        List<UserResponse> UserResponseList = getUserResponseList();

        return UserResponseList.stream().parallel()
                .filter(UserResponse -> UserResponse.getId().equals(id))
                .findAny();
    }

    public UserResponse insertUser(UserResponse UserResponse) {
        if (UserResponse == null) {
            throw new IllegalArgumentException("Cannot add null user");
        }

        UserResponseList.add(UserResponse);

        return UserResponse;
    }
}
