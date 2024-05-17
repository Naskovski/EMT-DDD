package emtddd.locationmanagement.domain.repository;

import emtddd.locationmanagement.domain.models.Location;
import emtddd.locationmanagement.domain.models.LocationID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationID> {
}
