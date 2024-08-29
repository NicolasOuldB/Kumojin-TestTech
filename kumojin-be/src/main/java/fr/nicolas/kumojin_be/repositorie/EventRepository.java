package fr.nicolas.kumojin_be.repositorie;

import fr.nicolas.kumojin_be.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
