package mk.ukim.finki.emt2025b.emt2025b.service.application;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateHostDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayHostDto;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.HostProjection;
import mk.ukim.finki.emt2025b.emt2025b.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto> update(Long id, CreateHostDto host);
    Optional<DisplayHostDto> save(CreateHostDto host);
    void deleteById(Long id);
    List<HostsPerCountryView> findHostsByCountry();
    List<HostProjection> getNamesAndSurnames();
}
