package club_website.auth.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1248060L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Date dateCreation;
    private Date ddl;

    @Builder.Default
    private boolean status = true;

    @Builder.Default
    private double score = 20;

    @ManyToOne
    @JoinColumn(name = "idDepartment")
    @JsonBackReference("task_dep")
    private Department department;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("task_work")
    private List<Work> works = new ArrayList<>();

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dateCreation=" + dateCreation
                + ", ddl=" + ddl + ", status=" + status + ", score=" + score + ", works=" + works + "]";
    }
}
