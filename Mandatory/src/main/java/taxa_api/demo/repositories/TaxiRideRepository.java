package taxa_api.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxa_api.demo.models.TaxiRide;

@Repository
public interface TaxiRideRepository extends JpaRepository<TaxiRide, Long> {
}
