package mk.ukim.finki.emt2025b.emt2025b.service.application.impl;

import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.ReservationsListDto;
import mk.ukim.finki.emt2025b.emt2025b.service.application.ReservationApplicationService;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {

    private final ReservationService reservationService;

    public ReservationApplicationServiceImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public List<DisplayAccommodationDto> listAllReservationsInList(Long reservationId) {
        return DisplayAccommodationDto.from(reservationService.listAllReservationsInList(reservationId));
    }

    @Override
    public Optional<ReservationsListDto> getActiveReservationList(String username) {
        return reservationService.getActiveReservationList(username).map(ReservationsListDto::from);
    }

    @Override
    public Optional<ReservationsListDto> addAccommodationToList(String username, Long accommodationId) {
        return reservationService.addAccommodationToList(username, accommodationId).map(ReservationsListDto::from);
    }

    @Override
    public void confirmReservations(String username, int numRooms) {
        reservationService.confirmReservations(username, numRooms);

    }
}
