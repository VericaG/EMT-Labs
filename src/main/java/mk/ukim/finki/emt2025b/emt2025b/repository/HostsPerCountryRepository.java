package mk.ukim.finki.emt2025b.emt2025b.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt2025b.emt2025b.model.views.HostsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostsPerCountryRepository extends JpaRepository<HostsPerCountryView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_per_country", nativeQuery = true)
    public void refreshMaterializedView();

    List<HostsPerCountryView> findAll();
}
