package mk.ukim.finki.emt2025b.emt2025b.model.exceptions;

public class AccommodationNotAvailableException extends RuntimeException {
    public AccommodationNotAvailableException(Long accommodationId) {
        super(String.format("Accommodation with id:%d is not available!", accommodationId));
    }
}
