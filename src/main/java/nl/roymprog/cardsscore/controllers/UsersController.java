package nl.roymprog.cardsscore.controllers;

import javassist.NotFoundException;
import nl.roymprog.cardsscore.models.entity.UserEntity;
import nl.roymprog.cardsscore.models.requests.UserRequest;
import nl.roymprog.cardsscore.models.response.UserResponse;
import nl.roymprog.cardsscore.repositories.UserDAO;
import nl.roymprog.cardsscore.util.UuidUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private UserDAO userDAO;

    ChineesPoepenController chineesPoepenController;

    public UsersController(UserDAO userDAO, ChineesPoepenController chineesPoepenController) {
        this.userDAO = userDAO;
        this.chineesPoepenController = chineesPoepenController;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        List<UserEntity> entities = userDAO.findAll();

        return buildUserResponses(entities);
    }

    @PostMapping(consumes = "application/json")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        Optional<UserEntity> entityOptional = userDAO.findByUsername(userRequest.getUsername());

        if (entityOptional.isPresent()) {
            throw new EntityExistsException("Cannot register user with this id, user already exists");
        }

        UserEntity entity = new UserEntity();
        entity.setId(UuidUtil.generateRandomId());
        entity.setUsername(userRequest.getUsername());

        userDAO.save(entity);

        return buildUserResponse(entity);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable String userId) throws NotFoundException {

        Optional<UserEntity> userOptional = userDAO.findById(UUID.fromString(userId));

        if (!userOptional.isPresent()) {
            throw new NotFoundException("User could not be found for id: " + userId);
        }

        return buildUserResponse(userOptional.get());
    }

    private UserResponse buildUserResponse(UserEntity entity) {
        return new UserResponse(entity.getId().toString(),
                entity.getJoinedOn(),
                entity.getUsername(),
                Collections.emptyList());
    }

    private List<UserResponse> buildUserResponses(List<UserEntity> entities) {
        return entities.stream()
                .map(entity -> buildUserResponse(entity))
                .collect(Collectors.toList());
    }
}
