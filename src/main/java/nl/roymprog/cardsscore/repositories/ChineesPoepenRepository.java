package nl.roymprog.cardsscore.repositories;

import nl.roymprog.cardsscore.models.entity.ChineesPoepen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChineesPoepenRepository extends JpaRepository<ChineesPoepen, UUID>{
}
