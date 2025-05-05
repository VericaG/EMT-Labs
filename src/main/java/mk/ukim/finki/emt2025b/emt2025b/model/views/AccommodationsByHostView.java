package mk.ukim.finki.emt2025b.emt2025b.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_per_host")
@Immutable
public class AccommodationsByHostView {

    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "num_rooms")
    private Integer numRooms;

    public AccommodationsByHostView(Long hostId, Integer numRooms) {
        this.hostId = hostId;
        this.numRooms = numRooms;
    }

    public AccommodationsByHostView() {
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}


