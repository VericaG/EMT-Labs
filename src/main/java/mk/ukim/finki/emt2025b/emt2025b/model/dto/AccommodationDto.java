package mk.ukim.finki.emt2025b.emt2025b.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt2025b.emt2025b.model.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;

@Data
@NoArgsConstructor
public class AccommodationDto {
    private String name;

    private String category;

    private Long host;

    private Integer numRooms;

    public AccommodationDto(String name, String category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
