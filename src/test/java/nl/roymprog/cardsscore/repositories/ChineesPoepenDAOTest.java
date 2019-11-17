package nl.roymprog.cardsscore.repositories;

import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ChineesPoepenDAOTest {

    @Autowired
    private ChineesPoepenDAO chineesPoepen;

    private ChineesPoepenEntity cpe;

    @Before
    public void setUp() {
//        cpe = new ChineesPoepenEntity(UUID.randomUUID(), UUID.randomUUID(),1);
        cpe = new ChineesPoepenEntity();
    }

    @Test
    public void saveEntity() {

        cpe = chineesPoepen.save(cpe);

        assertThat(chineesPoepen.findById(cpe.getId())).hasValue(cpe);
    }
}