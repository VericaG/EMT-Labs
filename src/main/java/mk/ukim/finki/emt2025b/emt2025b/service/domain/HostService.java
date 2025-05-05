package mk.ukim.finki.emt2025b.emt2025b.service.domain;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.dto.CreateHostDto;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.HostProjection;
import mk.ukim.finki.emt2025b.emt2025b.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> update(Long id,  Host host);

    Optional<Host> save(Host host);

    void deleteById(Long id);

    void refreshMaterializedView();

    List<HostsPerCountryView> findHostsByCountry();

    List<HostProjection> getNamesAndSurnames();
}
