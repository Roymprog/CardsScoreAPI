package nl.roymprog.cardsscore.configuration.chineespoepensm;

import nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChineesPoepenStateMachineFactory {

    private final StateMachineFactory<ChineesPoepenStates, ChineesPoepenEvents> factory;
    private final ChineesPoepenStateMachineInterceptor interceptor;

    @Autowired
    public ChineesPoepenStateMachineFactory(final StateMachineFactory<ChineesPoepenStates, ChineesPoepenEvents> factory, final ChineesPoepenStateMachineInterceptor interceptor) {
        this.factory = factory;
        this.interceptor = interceptor;
    }

    public StateMachine<ChineesPoepenStates, ChineesPoepenEvents> change(ChineesPoepenDTO dto) {
        StateMachine<ChineesPoepenStates, ChineesPoepenEvents> sm = this.build(dto);

        Message<ChineesPoepenEvents> message = MessageBuilder
                .withPayload(dto.getEvent())
                .setHeader(ChineesPoepenDTO.CHINEES_POEPEN_MESSAGE_HEADER, dto)
                .build();

        sm.sendEvent(message);

        return sm;
    }

    private StateMachine<ChineesPoepenStates,ChineesPoepenEvents> build(ChineesPoepenDTO dto) {
        StateMachine<ChineesPoepenStates, ChineesPoepenEvents> sm = this.factory.getStateMachine(dto.getId().toString());
        sm.stop();

        sm.getExtendedState().getVariables()
                .put(ChineesPoepenDTO.CHINEES_POEPEN_MESSAGE_HEADER, dto);
        this.interceptor.addSaveOnStateChangeInterceptor(sm);

        sm.start();
        return sm;
    }
}
