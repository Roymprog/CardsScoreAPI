package nl.roymprog.cardsscore.configuration.chineespoepensm;

import nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.test.AbstractStateMachineTests;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import org.springframework.statemachine.transition.Transition;

import static nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenEvents.*;
import static nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStates.*;
import static nl.roymprog.cardsscore.models.dto.ChineesPoepenDTO.CHINEES_POEPEN_MESSAGE_HEADER;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestConfiguration.class)
public class ChineesPoepenStatemachineTest extends AbstractStateMachineTests {

    @Autowired
    private StateMachineFactory<ChineesPoepenStates,ChineesPoepenEvents> factory;

    private StateMachine<ChineesPoepenStates, ChineesPoepenEvents> sm;

    private boolean triggered = false;

    @BeforeEach
    public void initSm() {
        triggered = false;
        sm = factory.getStateMachine();
        sm.start();
    }

    @Test
    public void smInitialState() throws Exception {
        StateMachineTestPlan<ChineesPoepenStates, ChineesPoepenEvents> plan =
                StateMachineTestPlanBuilder.<ChineesPoepenStates, ChineesPoepenEvents>builder()
                        .stateMachine(sm)
                        .step()
                        .expectState(ChineesPoepenStates.INITIATED)
                        .and()
                        .step()
                        .sendEvent(CREATE)
                        .expectState(CREATED)
                        .and()
//                        .step()
//                        .sendEvent(ACCEPT)
//                        .expectState(ACCEPTED_ONCE)
//                        .and()
//                        .step()
//                        .sendEvent(ACCEPT)
//                        .expectState(ACCEPTED_TWICE)
//                        .and()
//                        .step()
//                        .sendEvent(ACCEPT)
//                        .expectState(READY)
//                        .and()
//                        .step()
//                        .sendEvent(PLAY)
//                        .expectState(PLAYING)
//                        .and()
//                        .step()
//                        .sendEvent(PLAY)
//                        .expectState(PLAYING)
//                        .and()
                        .build();
        plan.test();
    }

    @Test
    public void smTestInterceptorTriggeredPostStateChanged() throws Exception {
        addPostStateChangedInterceptor(sm);

        StateMachineTestPlan<ChineesPoepenStates, ChineesPoepenEvents> plan =
                StateMachineTestPlanBuilder.<ChineesPoepenStates, ChineesPoepenEvents>builder()
                        .stateMachine(sm)
                        .step()
                        .sendEvent(CREATE)
                        .expectState(CREATED)
                        .and()
                        .build();
        plan.test();

        assertTrue(triggered);
    }

    private void addPostStateChangedInterceptor(StateMachine<ChineesPoepenStates, ChineesPoepenEvents> sm) {
        sm.getStateMachineAccessor()
                .doWithRegion(new StateMachineFunction<StateMachineAccess<ChineesPoepenStates, ChineesPoepenEvents>>() {
                    @Override
                    public void apply(StateMachineAccess<ChineesPoepenStates, ChineesPoepenEvents> sma) {
                        sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<ChineesPoepenStates, ChineesPoepenEvents>(){

                            @Override
                            public void postStateChange(State<ChineesPoepenStates, ChineesPoepenEvents> state, Message<ChineesPoepenEvents> message, Transition<ChineesPoepenStates, ChineesPoepenEvents> transition, StateMachine<ChineesPoepenStates, ChineesPoepenEvents> stateMachine, StateMachine<ChineesPoepenStates, ChineesPoepenEvents> rootStateMachine) {
                                triggered = true;
                            }
                        });
                    }
                });
    }
}

@Configuration
@Import(ChineesPoepenStateMachineConfiguration.class)
class TestConfiguration{

    Guard<ChineesPoepenStates, ChineesPoepenEvents> trueGuard = context -> true;

    @Bean
    public Guard<ChineesPoepenStates, ChineesPoepenEvents> validHost() {
        return trueGuard;
    };

    @Bean
    public Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers() {
        return trueGuard;
    };
}