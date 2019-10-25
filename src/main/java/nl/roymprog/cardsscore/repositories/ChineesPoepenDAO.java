package nl.roymprog.cardsscore.repositories;

import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChineesPoepenDAO extends JpaRepository<ChineesPoepenEntity, UUID> {
    List<ChineesPoepenEntity> findByHost(UUID uuid);
}
