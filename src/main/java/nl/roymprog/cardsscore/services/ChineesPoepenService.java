package nl.roymprog.cardsscore.services;

import nl.roymprog.cardsscore.models.entity.ChineesPoepen;
import nl.roymprog.cardsscore.repositories.ChineesPoepenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChineesPoepenService {

    private final ChineesPoepenRepository repository;

    public ChineesPoepenService(final ChineesPoepenRepository repository) {
        this.repository = repository;
    }

    public ChineesPoepen create() {
        return repository.save(new ChineesPoepen());
    }

    public ChineesPoepen upsert(ChineesPoepen entity) {
        return repository.save(entity);
    }

    public Optional<ChineesPoepen> findOne(UUID id) {
        return repository.findById(id);
    }
}
