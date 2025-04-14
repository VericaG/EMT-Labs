package mk.ukim.finki.emt2025b.emt2025b.repository;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.ReservationsList;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationsList, Long> {
    Optional<ReservationsList> findByUser(User user);
}
