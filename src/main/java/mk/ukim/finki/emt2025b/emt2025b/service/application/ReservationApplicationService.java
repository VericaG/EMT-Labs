package mk.ukim.finki.emt2025b.emt2025b.service.application;

import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.ReservationsListDto;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {
    List<DisplayAccommodationDto> listAllReservationsInList(Long reservationId);

    Optional<ReservationsListDto> getActiveReservationList(String username);

    Optional<ReservationsListDto> addAccommodationToList(String username, Long accommodationId);
    void confirmReservations(String username, int numRooms);


}
