package nl.roymprog.cardsscore.repositories;

import nl.roymprog.cardsscore.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
