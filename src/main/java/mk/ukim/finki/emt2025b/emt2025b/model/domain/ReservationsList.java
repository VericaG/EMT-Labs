package mk.ukim.finki.emt2025b.emt2025b.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ReservationsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Accommodation> accommodations;

    public ReservationsList(User user) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.accommodations = accommodations;
    }
}


