package mk.ukim.finki.emt2025b.emt2025b.repository;

import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    @Query("select h.name AS name, h.surname AS surname from Host h")
    List<HostProjection> getNameAndSurnameProjection();
}
