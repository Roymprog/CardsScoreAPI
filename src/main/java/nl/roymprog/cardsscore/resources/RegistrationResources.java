package nl.roymprog.cardsscore.resources;

import nl.roymprog.cardsscore.controllers.UsersController;
import nl.roymprog.cardsscore.models.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationResources {

    private UsersController usersController;

    public RegistrationResources(UsersController usersController) {
        this.usersController = usersController;
    }

    @PostMapping
    public User register(@RequestBody User user) {
        User newUser = usersController.createUser(user);

        return newUser;
    }

}
