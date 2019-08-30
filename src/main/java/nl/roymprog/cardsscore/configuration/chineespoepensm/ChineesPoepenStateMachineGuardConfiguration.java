package nl.roymprog.cardsscore.configuration.chineespoepensm;

import nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import static nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO.*;

@Configuration
public class ChineesPoepenStateMachineGuardConfiguration {
    @Bean
    public Guard<ChineesPoepenStates, ChineesPoepenEvents> validHost() {
        return new Guard<ChineesPoepenStates, ChineesPoepenEvents>() {

            @Override
            public boolean evaluate(StateContext<ChineesPoepenStates, ChineesPoepenEvents> context) {
                ChineesPoepenDTO dto = context.getExtendedState()
                        .get(CHINEES_POEPEN_MESSAGE_HEADER, ChineesPoepenDTO.class);

                return dto.getHostId() != null;
            }
        };
    }

    @Bean
    public Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers() {
        return new Guard<ChineesPoepenStates, ChineesPoepenEvents>() {

            @Override
            public boolean evaluate(StateContext<ChineesPoepenStates, ChineesPoepenEvents> context) {
                ChineesPoepenDTO dto = context.getExtendedState()
                        .get(CHINEES_POEPEN_MESSAGE_HEADER, ChineesPoepenDTO.class);

                return dto.getPlayers().size() == NUMBER_OF_PLAYERS && dto.getPlayers().stream()
                        .noneMatch(player -> player == null);
            }
        };
    }
}
