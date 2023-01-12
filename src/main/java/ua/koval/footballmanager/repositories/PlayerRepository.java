package ua.koval.footballmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.koval.footballmanager.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
