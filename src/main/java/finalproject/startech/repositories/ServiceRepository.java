package finalproject.startech.repositories;

import finalproject.startech.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Feature,Long> {
}
