package mk.ukim.finki.emt2025b.emt2025b.dto;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.ReservationsList;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationsListDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayAccommodationDto> accommodations
) {
    public static ReservationsListDto from(ReservationsList reservationsList) {
        return new ReservationsListDto(
                reservationsList.getId(),
                reservationsList.getDateCreated(),
                DisplayUserDto.from(reservationsList.getUser()),
                DisplayAccommodationDto.from(reservationsList.getAccommodations())
        );
    }
}
