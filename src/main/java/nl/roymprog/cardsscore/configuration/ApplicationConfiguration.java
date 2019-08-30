package nl.roymprog.cardsscore.configuration;

import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStateMachineConfiguration;
import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStateMachineGuardConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ChineesPoepenStateMachineConfiguration.class, ChineesPoepenStateMachineGuardConfiguration.class})
public class ApplicationConfiguration {
}
