package Hope.controller.feedback;

import Hope.exceptions.ResourceNotFoundException;
import Hope.model.Feedback;
import Hope.model.FeedbackRepository;
import Hope.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public void addComment(int id, User user, String comment) {
        feedbackRepository.addComment(user.getId(), id, comment);
    }

    public List<Feedback> getComments(int id) {
        return feedbackRepository.findAllByToolId(id).orElseThrow(() -> new ResourceNotFoundException("Feedback", "tool_id", id));
    }
}
