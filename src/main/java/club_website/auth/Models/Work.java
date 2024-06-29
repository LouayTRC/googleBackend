package club_website.auth.Models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Work")
public class Work implements Serializable {

    private static final long serialVersionUID = 29147510L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder.Default
    private String url = "";

    @Builder.Default
    private double score = 0;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference("task_work")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user_work")
    private User user;

    @Builder.Default
    private Date dateDepos = new Date();

    @Builder.Default
    private boolean status = true;

    @Override
    public String toString() {
        return "Work [id=" + id + ", url=" + url + ", score=" + score + ", user=" + user + ", dateDepos=" + dateDepos
                + ", status=" + status + "]";
    }
}
