package mk.ukim.finki.emt2025b.emt2025b.repository;

import mk.ukim.finki.emt2025b.emt2025b.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
