package mk.ukim.finki.emt2025b.emt2025b.service.application;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    Optional<DisplayAccommodationDto> update (Long id, CreateAccommodationDto accommodationDto);
    Optional<DisplayAccommodationDto> save (CreateAccommodationDto accommodationDto);
    Optional<DisplayAccommodationDto> findById (Long id);
    List<DisplayAccommodationDto> findAll();

    void reserveAccommodation(Long accommodationId, int rooms);
    List<DisplayAccommodationDto> findRecommendedAccomodations(AccommodationCategory category, Long accommodationId);

    void deleteById (Long id);
}
