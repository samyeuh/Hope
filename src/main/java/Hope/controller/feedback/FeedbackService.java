package Hope.controller.feedback;

import Hope.exceptions.ResourceNotFoundException;
import Hope.model.Feedback;
import Hope.model.FeedbackRepository;
import Hope.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public void addComment(int id, User user, String comment) {
        logger.info("Ajout d'un commentaire pour l'outil ID: {} par l'utilisateur '{}'", id, user.getUsername());
        try {
            feedbackRepository.addComment(user.getId(), id, comment);
            logger.info("Commentaire ajouté avec succès.");
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout du commentaire pour l'outil ID: {} : {}", id, e.getMessage());
            throw e;
        }
    }


    public List<Feedback> getComments(int id) {
        logger.info("Récupération des commentaires pour l'outil ID: {}", id);
        return feedbackRepository.findAllByToolId(id).orElseThrow(() -> new ResourceNotFoundException("Feedback", "tool_id", id));
    }
}
