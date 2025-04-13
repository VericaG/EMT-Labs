package mk.ukim.finki.emt2025b.emt2025b.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Country;


public record CreateCountryDto (
         String name,
         String continent

) {

    public Country toCountry() {
        return new Country(name, continent);
    }
}
