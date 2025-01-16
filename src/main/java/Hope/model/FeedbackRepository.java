package Hope.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    Optional<List<Feedback>> findAllByToolId(int toolId);

    @Modifying
    @Query(value = "INSERT INTO feedback (user_id, tool_id, commentaire) VALUES (?, ?, ?)", nativeQuery = true)
    void addComment(long userId, int toolId, String comment);

    Optional<Feedback> hasCommented(long userId, int toolId);
}
