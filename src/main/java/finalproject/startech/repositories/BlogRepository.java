package finalproject.startech.repositories;

import finalproject.startech.dtos.blogdtos.BlogRecentDto;
import finalproject.startech.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("SELECT b FROM Blog b WHERE b.title like %?1% AND b.isDeleted = false ")
    List<Blog> findByTitle(String title);

    @Query("SELECT b FROM Blog b ORDER BY b.createdDate DESC")
    List<Blog> findLatest3Blogs();

    @Query("SELECT b FROM Blog b ORDER BY b.createdDate DESC")
    List<Blog> findLatest5Blogs();
}
