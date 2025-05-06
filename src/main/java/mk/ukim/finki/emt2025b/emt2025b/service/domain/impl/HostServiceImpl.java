package mk.ukim.finki.emt2025b.emt2025b.service.domain.impl;

import mk.ukim.finki.emt2025b.emt2025b.events.HostEvent;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.HostProjection;
import mk.ukim.finki.emt2025b.emt2025b.model.views.HostsPerCountryView;
import mk.ukim.finki.emt2025b.emt2025b.repository.HostRepository;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.CountryService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsPerCountryRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existingHost -> {
                    if (host.getName() != null) {
                        existingHost.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existingHost.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
                        existingHost.setCountry(countryService.findById(host.getCountry().getId()).get());
                    }
                    Host updatedHost = hostRepository.save(existingHost);
                    applicationEventPublisher.publishEvent(updatedHost);
                    return updatedHost;
                });
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
        if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
            savedHost = Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(), countryService.findById(host.getCountry().getId()).get()))
            );
            applicationEventPublisher.publishEvent(new HostEvent(savedHost.get()));
        }
        return savedHost;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Host> host = this.findById(id);
        hostRepository.deleteById(id);
        applicationEventPublisher.publishEvent(new HostEvent(host.get()));
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostsPerCountryView> findHostsByCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostRepository.getNameAndSurnameProjection();
    }
}
