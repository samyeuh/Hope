package Hope.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "feedback")
@NamedQueries({
        @NamedQuery(
                name = "Feedback.findAllByToolId",
                query = "SELECT f FROM Feedback f WHERE f.tool.id = :toolId"
        ),
        @NamedQuery(
                name = "Feedback.hasCommented",
                query = "SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.tool.id = :toolId"
        )
})
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    @NotBlank(message = "Le commentaire ne peut pas être vide.")
    @Size(max = 1000, message = "Le commentaire ne peut pas dépasser 1000 caractères.")
    @Lob
    @Column(name = "commentaire", nullable = false)
    private String commentaire;

    @ColumnDefault("curdate()")
    @PastOrPresent(message = "La date doit être aujourd'hui ou dans le passé.")
    @Column(name = "date")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isEqual(date)) {
            return "Aujourd'hui";
        } else {
            return date.format(formatter);
        }
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}