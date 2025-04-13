package mk.ukim.finki.emt2025b.emt2025b.service.domain.impl;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.dto.CreateAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt2025b.emt2025b.repository.AccommodationRepository;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.AccommodationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.CountryService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final CountryService countryService;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, CountryService countryService, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.countryService = countryService;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id)
                .map(existingAcc -> {
                    if(accommodation.getName() != null) {
                        existingAcc.setName(accommodation.getName());
                    }
                    if(accommodation.getCategory() != null) {
                        existingAcc.setCategory(AccommodationCategory.valueOf(accommodation.getCategory().name()));
                    }
                    if(accommodation.getNumRooms() != null) {
                        existingAcc.setNumRooms(accommodation.getNumRooms());
                    }
                    if(accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                        existingAcc.setHost(hostService.findById(accommodation.getHost().getId()).get());
                    }
                    return accommodationRepository.save(existingAcc);
                });
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if(accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(), AccommodationCategory.valueOf(accommodation.getCategory().name()), hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms()))
            );
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public void reserveAccommodation(Long accommodationId, int rooms) {
        Accommodation accommodation = this.findById(accommodationId).get();
        if(accommodation.getNumRooms() >= rooms) {
            accommodation.setNumRooms(accommodation.getNumRooms() - rooms);
        }
        accommodationRepository.save(accommodation);
    }

    @Override
    public List<Accommodation> findRecommendedAccomodations(AccommodationCategory category, Long accommodationId) {
        Accommodation accommodation = this.findById(accommodationId).get();
        AccommodationCategory cat = accommodation.getCategory();
        return this.accommodationRepository.findAllByCategoryAndIdNot(cat, accommodationId);
    }


}
