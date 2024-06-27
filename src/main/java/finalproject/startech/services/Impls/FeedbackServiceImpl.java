package finalproject.startech.services.Impls;


import finalproject.startech.dtos.feedbackdtos.FeedbackCreateDto;
import finalproject.startech.dtos.feedbackdtos.FeedbackDto;
import finalproject.startech.models.Feedback;
import finalproject.startech.models.UserEntity;
import finalproject.startech.repositories.FeedbackRepository;
import finalproject.startech.repositories.UserRepository;
import finalproject.startech.services.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl  implements FeedbackService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addFeedback(FeedbackCreateDto feedbackCreateDto) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(feedbackCreateDto.getFeedback());
        Long userId = feedbackCreateDto.getUserId();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        feedback.setUser(user);
        feedback.setPhotoUrl(feedbackCreateDto.getPhotoUrl());
        feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream().map(feedback -> {
            FeedbackDto feedbackDto = modelMapper.map(feedback, FeedbackDto.class);
            UserEntity user = userRepository.findById(feedback.getUser().getId()).orElseThrow();
            feedbackDto.setFirstName(user.getFirstName());
            feedbackDto.setLastName(user.getLastName());
            return feedbackDto;
        }).collect(Collectors.toList());
    }
}
