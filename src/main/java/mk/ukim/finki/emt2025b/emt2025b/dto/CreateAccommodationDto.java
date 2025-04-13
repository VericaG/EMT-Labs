package mk.ukim.finki.emt2025b.emt2025b.dto;

import jdk.jfr.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.stream.Collectors;


public record CreateAccommodationDto(
        String name,
        AccommodationCategory category,
        Long host,
        Integer numRooms
) {
    public static CreateAccommodationDto from (Accommodation accommodation) {
        return new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }

    public static List<CreateAccommodationDto> from (List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }
}
