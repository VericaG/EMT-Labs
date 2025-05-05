package mk.ukim.finki.emt2025b.emt2025b.repository;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.User;

import jakarta.validation.constraints.DecimalMin;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.Role;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {}
    )
    @Query("select u from User u")
    List<User> fetchAll();

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}


