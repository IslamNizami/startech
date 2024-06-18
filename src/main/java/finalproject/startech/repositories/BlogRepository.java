package finalproject.startech.repositories;

import finalproject.startech.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("SELECT b FROM Blog b WHERE b.title like %?1%")
    List<Blog> findByTitle(String title);
}
