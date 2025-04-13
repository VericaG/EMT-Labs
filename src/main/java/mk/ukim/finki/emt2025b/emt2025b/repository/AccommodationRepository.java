package mk.ukim.finki.emt2025b.emt2025b.repository;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    public List<Accommodation> findAllByCategoryAndIdNot(AccommodationCategory category, Long id);

    Long id(Long id);
}
