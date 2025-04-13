package mk.ukim.finki.emt2025b.emt2025b.dto;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        AccommodationCategory category,
        Long host,
        Integer numRooms
) {
    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }

    public static List<DisplayAccommodationDto> from (List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }
}
