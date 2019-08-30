package nl.roymprog.cardsscore.configuration.chineespoepensm;

import nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChineesPoepenStateMachineGuardConfigurationTest {
    private final ChineesPoepenStateMachineGuardConfiguration sut = new ChineesPoepenStateMachineGuardConfiguration();

    @Mock
    private StateContext<ChineesPoepenStates, ChineesPoepenEvents> context;

    @Mock
    ExtendedState extendedState;

    private ChineesPoepenDTO dto;

    @Before
    public void setUp() {
        dto = new ChineesPoepenDTO();
        dto.setHostId(UUID.randomUUID());
        List<UUID> playerList = new ArrayList<>();
        for (int i = 0; i < ChineesPoepenDTO.NUMBER_OF_PLAYERS; i++) {
            playerList.add(UUID.randomUUID());
        }
        dto.setPlayers(playerList);

        when(context.getExtendedState()).thenReturn(extendedState);
        when(extendedState.get(anyString(), eq(ChineesPoepenDTO.class))).thenReturn(dto);
    }

    @Test
    public void validHost() {
        Guard<ChineesPoepenStates, ChineesPoepenEvents> validHost = sut.validHost();

        assertTrue(validHost.evaluate(context));
    }

    @Test
    public void invalidHost() {
        Guard<ChineesPoepenStates, ChineesPoepenEvents> validHost = sut.validHost();

        dto.setHostId(null);

        assertFalse(validHost.evaluate(context));
    }

    @Test
    public void validPlayers() {
        Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers = sut.validPlayers();

        assertTrue(validPlayers.evaluate(context));
    }

    @Test
    public void invalidPlayer() {
        Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers = sut.validPlayers();

        List<UUID> players = dto.getPlayers();
        players.add(null);

        assertFalse(validPlayers.evaluate(context));
    }

    @Test
    public void invalidPlayerCount() {
        Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers = sut.validPlayers();

        List<UUID> players = dto.getPlayers();
        players.remove(0);

        assertFalse(validPlayers.evaluate(context));
    }
}