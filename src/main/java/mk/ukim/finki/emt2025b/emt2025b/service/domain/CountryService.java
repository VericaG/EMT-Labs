package mk.ukim.finki.emt2025b.emt2025b.service.domain;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Country;
import mk.ukim.finki.emt2025b.emt2025b.dto.CreateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> save(Country country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);

}
