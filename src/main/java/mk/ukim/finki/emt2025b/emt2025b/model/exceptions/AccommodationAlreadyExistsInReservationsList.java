package mk.ukim.finki.emt2025b.emt2025b.model.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class AccommodationAlreadyExistsInReservationsList extends RuntimeException {
    public AccommodationAlreadyExistsInReservationsList(Long id, String username) {
        super(String.format("Accommodation with id: %d already exists in reservation list for user with username %s", id, username));
    }
}