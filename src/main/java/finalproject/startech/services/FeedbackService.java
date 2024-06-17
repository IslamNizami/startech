package finalproject.startech.services;

import finalproject.startech.dtos.feedbackdtos.FeedbackCreateDto;

public interface FeedbackService {

    void addFeedback(FeedbackCreateDto feedbackCreateDto);
}
