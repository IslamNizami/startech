package finalproject.startech.services;

import finalproject.startech.dtos.feedbackdtos.FeedbackCreateDto;
import finalproject.startech.dtos.feedbackdtos.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    void addFeedback(FeedbackCreateDto feedbackCreateDto);
    List<FeedbackDto> getAllFeedbacks();
}
