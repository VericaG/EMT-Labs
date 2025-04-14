package mk.ukim.finki.emt2025b.emt2025b.service.domain;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.ReservationsList;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Accommodation> listAllReservationsInList(Long reservationId);

    Optional<ReservationsList> getActiveReservationList(String username);

    Optional<ReservationsList> addAccommodationToList(String username, Long accomodationId);

    void confirmReservations(String username, int numRooms);
}
