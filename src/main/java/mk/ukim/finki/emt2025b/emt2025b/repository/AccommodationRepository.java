package mk.ukim.finki.emt2025b.emt2025b.repository;

import jdk.jfr.Category;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {


  //  @Query("SELECT a FROM Accommodation a WHERE a.category = :category AND a.id <> :id")
   // public List<Accommodation> findAllByCategoryAndIdNot(@Param("category") AccommodationCategory category, @Param("id") Long id);

  Optional<Accommodation> findAllByCategoryAndIdNot(AccommodationCategory category, Long id);

}
