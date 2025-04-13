package mk.ukim.finki.emt2025b.emt2025b.service;

import jdk.jfr.Category;
import mk.ukim.finki.emt2025b.emt2025b.model.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, AccommodationDto accommodationDto);

    Optional<Accommodation> save(AccommodationDto accommodation);

    void deleteById(Long id);
    void reserveAccommodation(Long accommodationId, int rooms);
    List<Accommodation> findRecommendedAccomodations(AccommodationCategory category,Long accommodationId);

}
