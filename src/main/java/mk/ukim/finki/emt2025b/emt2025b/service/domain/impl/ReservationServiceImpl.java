package mk.ukim.finki.emt2025b.emt2025b.service.domain.impl;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.ReservationsList;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.User;
import mk.ukim.finki.emt2025b.emt2025b.model.exceptions.AccommodationAlreadyExistsInReservationsList;
import mk.ukim.finki.emt2025b.emt2025b.model.exceptions.AccommodationNotAvailableException;
import mk.ukim.finki.emt2025b.emt2025b.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.emt2025b.emt2025b.model.exceptions.ReservationListNotFoundException;
import mk.ukim.finki.emt2025b.emt2025b.repository.ReservationRepository;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.AccommodationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.ReservationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, AccommodationService accommodationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllReservationsInList(Long reservationId) {
        if (reservationRepository.findById(reservationId).isEmpty()) {
            throw new ReservationListNotFoundException(reservationId);
        }
        return reservationRepository.findById(reservationId).get().getAccommodations();
    }

    @Override
    public Optional<ReservationsList> getActiveReservationList(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationRepository.findByUser(user).orElseGet(() -> reservationRepository.save(new ReservationsList(user))));
    }

    @Override
    public Optional<ReservationsList> addAccommodationToList(String username, Long accomodationId) {
        if (getActiveReservationList(username).isPresent()) {
            ReservationsList reservationsList = getActiveReservationList(username).get();
            Accommodation accommodation = accommodationService.findById(accomodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accomodationId));
            if (!reservationsList.getAccommodations().stream().filter(i -> i.getId().equals(accomodationId)).toList().isEmpty())
                throw new AccommodationAlreadyExistsInReservationsList(accomodationId, username);

            if(accommodation.getNumRooms() <= 0){
                throw new AccommodationNotAvailableException(accomodationId);
            }

            reservationsList.getAccommodations().add(accommodation);
            return Optional.of(reservationRepository.save(reservationsList));

        }
        return Optional.empty();
    }

    @Override
    public void confirmReservations(String username, int numRooms) {
        ReservationsList reservationsList = getActiveReservationList(username).orElseThrow(() -> new ReservationListNotFoundException(username));

        for (Accommodation accommodation : reservationsList.getAccommodations()) {
            if (accommodation.getNumRooms() <= numRooms) {
                throw new AccommodationNotAvailableException(accommodation.getId());
            }
            accommodationService.reserveAccommodation(accommodation.getId(), numRooms);
        }
        reservationsList.setAccommodations(new ArrayList<>());
        reservationRepository.save(reservationsList);
    }
}
