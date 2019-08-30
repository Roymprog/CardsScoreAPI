package nl.roymprog.cardsscore.configuration.chineespoepensm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;


@Configuration
public class ChineesPoepenStateMachineActionsConfiguration {

    @Bean
    public Action<ChineesPoepenStates, ChineesPoepenEvents> persistDtoAction() {
        return new Action<ChineesPoepenStates, ChineesPoepenEvents>() {
            @Override
            public void execute(StateContext<ChineesPoepenStates, ChineesPoepenEvents> stateContext) {

            }
        };
    }
}
