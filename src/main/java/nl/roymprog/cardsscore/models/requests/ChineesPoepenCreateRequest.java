package nl.roymprog.cardsscore.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class ChineesPoepenCreateRequest {
    @NotNull
    private Set<String> players;
    private int round;
    private String host;
}
