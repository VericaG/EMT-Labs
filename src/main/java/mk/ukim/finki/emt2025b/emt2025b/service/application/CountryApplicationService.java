package mk.ukim.finki.emt2025b.emt2025b.service.application;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateCountryDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayCountryDto;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    void deleteById(Long id);
}
