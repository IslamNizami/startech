package finalproject.startech.repositories;

import finalproject.startech.models.Feedback;
import finalproject.startech.services.FeedbackService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
