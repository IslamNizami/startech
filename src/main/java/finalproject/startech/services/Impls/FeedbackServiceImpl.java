package finalproject.startech.services.Impls;


import finalproject.startech.dtos.feedbackdtos.FeedbackCreateDto;
import finalproject.startech.models.Feedback;
import finalproject.startech.models.UserEntity;
import finalproject.startech.repositories.FeedbackRepository;
import finalproject.startech.repositories.UserRepository;
import finalproject.startech.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl  implements FeedbackService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void addFeedback(FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(feedbackCreateDto.getFeedback());
        Long userId = feedbackCreateDto.getUserId();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        feedback.setUser(user);
        feedbackRepository.save(feedback);
    }
}
