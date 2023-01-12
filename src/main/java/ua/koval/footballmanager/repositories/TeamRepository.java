package ua.koval.footballmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.koval.footballmanager.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
