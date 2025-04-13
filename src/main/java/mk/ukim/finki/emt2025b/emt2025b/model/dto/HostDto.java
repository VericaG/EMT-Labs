package mk.ukim.finki.emt2025b.emt2025b.model.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt2025b.emt2025b.model.Country;

@Data
@NoArgsConstructor
public class HostDto {
    private String name;

    private String surname;

    private Long country;

    public HostDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
