package mk.ukim.finki.emt2025b.emt2025b.service.application.impl;

import mk.ukim.finki.emt2025b.emt2025b.dto.CreateAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt2025b.emt2025b.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.AccommodationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.CountryService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final CountryService countryService;


    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, CountryService countryService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto) {
        Optional<Host> host = hostService.findById(accommodationDto.host());
        return accommodationService.update(id,
                accommodationDto.toAccommodation(
                        host.orElse(null)
                )
        )
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto) {
        Optional<Host> host = hostService.findById(accommodationDto.host());

        if(host.isPresent()) {
            return accommodationService.save(accommodationDto.toAccommodation(host.get()))
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public void reserveAccommodation(Long accommodationId, int rooms) {
        Accommodation accommodation = accommodationService.findById(accommodationId).get();
        if(accommodation.getNumRooms() >= rooms) {
            accommodation.setNumRooms(accommodation.getNumRooms() - rooms);
        }
        accommodationService.save(accommodation);
    }

    @Override
    public List<DisplayAccommodationDto> findRecommendedAccomodations(AccommodationCategory category, Long accommodationId) {
        return accommodationService.findRecommendedAccomodations(category, accommodationId).stream()
                .map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }
}
