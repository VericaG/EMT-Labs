package mk.ukim.finki.emt2025b.emt2025b.service.domain;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.dto.CreateAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> save(Accommodation accommodation);

    void deleteById(Long id);
    void reserveAccommodation(Long accommodationId, int rooms);
    List<Accommodation> findRecommendedAccomodations(AccommodationCategory category,Long accommodationId);

}
