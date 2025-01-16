package Hope.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tool")
@NamedQueries({
        @NamedQuery(
                name = "Tool.search",
                query = "SELECT t FROM Tool t WHERE " +
                        "LOWER(t.titre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                        "LOWER(t.domaine) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                        "LOWER(CAST(t.descriptionSimple AS string)) LIKE LOWER(CONCAT('%', :query, '%'))"
        )
})
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Le titre est obligatoire.")
    @Size(max = 255, message = "Le titre ne peut pas dépasser 255 caractères.")
    @Column(name = "Titre", nullable = false)
    private String titre;

    @NotBlank(message = "Le domaine est obligatoire.")
    @Size(max = 255, message = "Le domaine ne peut pas dépasser 255 caractères.")
    @Column(name = "Domaine", nullable = false)
    private String domaine;

    @NotBlank(message = "Le lien est obligatoire.")
    @Lob
    @Column(name = "Lien", nullable = false)
    private String lien;

    @Size(max = 500, message = "La description simple ne peut pas dépasser 500 caractères.")
    @Lob
    @Column(name = "Description_simple")
    private String descriptionSimple;

    @Lob
    @Column(name = "Description_detaillee")
    private String descriptionDetaillee;

    @Lob
    @Column(name = "Acces")
    private String acces;

    @NotNull(message = "La visibilité doit être définie.")
    @ColumnDefault("1")
    @Column(name = "VISIBLE")
    private Boolean visible;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetaillee() {
        return descriptionDetaillee;
    }

    public void setDescriptionDetaillee(String descriptionDetaillee) {
        this.descriptionDetaillee = descriptionDetaillee;
    }

    public String getAcces() {
        return acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}