package finalproject.startech.repositories;

import finalproject.startech.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Tag> findAllById(Iterable<Long> ids);

}
