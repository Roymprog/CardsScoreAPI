package nl.roymprog.cardsscore.repositories;

import nl.roymprog.cardsscore.models.entity.ChineesPoepenEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChineesPoepenDAO extends PagingAndSortingRepository<ChineesPoepenEntity, UUID> {
    List<ChineesPoepenEntity> findByHost(UUID uuid);
}
