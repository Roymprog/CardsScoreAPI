package nl.roymprog.cardsscore.configuration.chineespoepensm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import static nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenEvents.*;
import static nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStates.*;
import static nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStates.ERROR;

@Configuration
@EnableStateMachineFactory
public class ChineesPoepenStateMachineConfiguration extends StateMachineConfigurerAdapter<ChineesPoepenStates, ChineesPoepenEvents> {

    private final Guard<ChineesPoepenStates, ChineesPoepenEvents> validHost;
    private final Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers;

    @Autowired
    public ChineesPoepenStateMachineConfiguration(Guard<ChineesPoepenStates, ChineesPoepenEvents> validHost, Guard<ChineesPoepenStates, ChineesPoepenEvents> validPlayers) {
        this.validHost = validHost;
        this.validPlayers = validPlayers;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<ChineesPoepenStates, ChineesPoepenEvents> config) throws Exception {
        config.withConfiguration()
                .autoStartup(false);
    }

    @Override
    public void configure(StateMachineStateConfigurer<ChineesPoepenStates, ChineesPoepenEvents> states) throws Exception {
        states
            .withStates()
            .initial(INITIATED)
            .state(CREATED)
            .state(ACCEPTED_ONCE)
            .state(ACCEPTED_TWICE)
            .state(READY)
            .state(PLAYING)
            .end(ERROR)
            .end(REJECTED)
            .end(FINISHED)
            .end(CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ChineesPoepenStates, ChineesPoepenEvents> transitions) throws Exception {
        transitions
            .withExternal()
                .source(INITIATED).target(CREATED)
                .event(CREATE)
                .guard(validHost)
                .guard(validPlayers)
            .and()
            .withExternal().source(CREATED).target(ACCEPTED_ONCE).event(ACCEPT)
            .and()
            .withExternal().source(ACCEPTED_ONCE).target(ACCEPTED_TWICE).event(ACCEPT)
            .and()
            .withExternal().source(ACCEPTED_TWICE).target(READY).event(ACCEPT)
            .and()
            .withExternal().source(READY).target(PLAYING).event(PLAY)
            .and()
            .withExternal().source(PLAYING).target(PLAYING).event(PLAY)
            .and()
            .withExternal().source(CREATED).target(REJECTED).event(REJECT)
            .and()
            .withExternal().source(ACCEPTED_ONCE).target(REJECTED).event(REJECT)
            .and()
            .withExternal().source(ACCEPTED_TWICE).target(REJECTED).event(REJECT)
            .and()
            .withExternal().source(PLAYING).target(CANCELLED).event(CANCEL)
            .and()
            .withExternal().source(PLAYING).target(FINISHED).event(FINISH)
//            .and()
//            .withExternal().source(INITIATED).target(ERROR).event(CREATE)
//            .and()
//            .withExternal().source(CREATED).target(ERROR).event(ACCEPT)
//            .and()
//            .withExternal().source(ACCEPTED_ONCE).target(ERROR).event(ACCEPT)
//            .and()
//            .withExternal().source(ACCEPTED_TWICE).target(ERROR).event(ACCEPT)
//            .and()
//            .withExternal().source(READY).target(ERROR).event(PLAY)
//            .and()
//            .withExternal().source(PLAYING).target(ERROR).event(PLAY)
//            .and()
//            .withExternal().source(PLAYING).target(ERROR).event(FINISH)
        ;
    }
}
