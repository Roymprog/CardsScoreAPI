package nl.roymprog.cardsscore.configuration.chineespoepensm;

import nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO;
import nl.roymprog.cardsscore.models.entity.ChineesPoepen;
import nl.roymprog.cardsscore.services.ChineesPoepenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
public class ChineesPoepenStateMachineInterceptor {

    private final ChineesPoepenService service;

    @Autowired
    public ChineesPoepenStateMachineInterceptor(ChineesPoepenService service) {
        this.service = service;
    }

    public void addSaveOnStateChangeInterceptor(StateMachine<ChineesPoepenStates, ChineesPoepenEvents> sm) {
        sm.getStateMachineAccessor()
            .doWithRegion(new StateMachineFunction<StateMachineAccess<ChineesPoepenStates, ChineesPoepenEvents>>() {

                @Override
                public void apply(StateMachineAccess<ChineesPoepenStates, ChineesPoepenEvents> sma) {
                    sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<ChineesPoepenStates, ChineesPoepenEvents>() {
                        @Override
                        public void postStateChange(State<ChineesPoepenStates, ChineesPoepenEvents> state, Message<ChineesPoepenEvents> message, Transition<ChineesPoepenStates, ChineesPoepenEvents> transition, StateMachine<ChineesPoepenStates, ChineesPoepenEvents> stateMachine) {
                            ChineesPoepenDTO dto = sm.getExtendedState().get(ChineesPoepenDTO.CHINEES_POEPEN_MESSAGE_HEADER, ChineesPoepenDTO.class);
                            ChineesPoepen game = service.findOne(dto.getId()).get();
                            game.setGameState(state.getId());
                            service.upsert(game);
                        }
                    });
                }
            });
    }
}
