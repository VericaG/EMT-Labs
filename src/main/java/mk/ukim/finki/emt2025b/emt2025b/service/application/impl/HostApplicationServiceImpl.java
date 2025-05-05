package mk.ukim.finki.emt2025b.emt2025b.service.application.impl;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateHostDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayHostDto;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.HostProjection;
import mk.ukim.finki.emt2025b.emt2025b.model.views.HostsPerCountryView;
import mk.ukim.finki.emt2025b.emt2025b.service.application.HostApplicationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;

    public HostApplicationServiceImpl(HostService hostService) {
        this.hostService = hostService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        return hostService.update(id, host.toHost())
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        return hostService.save(host.toHost()).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostsPerCountryView> findHostsByCountry() {
       return hostService.findHostsByCountry();
    }

    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostService.getNamesAndSurnames();
    }
}
