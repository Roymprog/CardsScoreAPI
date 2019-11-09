package nl.roymprog.cardsscore;

import nl.roymprog.cardsscore.controllers.ChineesPoepenController;
import nl.roymprog.cardsscore.controllers.UsersController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    ChineesPoepenController chineesPoepenController;

    @Autowired
    UsersController usersController;

    @Test
    public void contextLoads() {
        assertThat(chineesPoepenController).isNotNull();
        assertThat(usersController).isNotNull();
    }
}