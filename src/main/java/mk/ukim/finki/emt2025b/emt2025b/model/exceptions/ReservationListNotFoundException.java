package mk.ukim.finki.emt2025b.emt2025b.model.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationListNotFoundException extends RuntimeException{
    public ReservationListNotFoundException(Long reservationsListId){
        super(String.format("List with id:%d not found.",reservationsListId));
    }

    public ReservationListNotFoundException(String username){
        super(String.format("List for user with username:%s not found.",username));
    }

}
