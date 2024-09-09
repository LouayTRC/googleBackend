package club_website.auth.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private double note = 0;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
//    @JsonBackReference("task_work")
    @JsonIgnoreProperties({"works","createdBy","updatedBy","deletedBy"})
    private Task task;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
//    @JsonBackReference("user_work")
    @JsonIgnoreProperties({"department", "works"})
    private Member member;

    @Builder.Default
    private LocalDateTime dateDepo = LocalDateTime.now();

    @Builder.Default
    private boolean status = true;
    
    
    @ManyToOne()
    @JoinColumn(name = "update_by_id")
	private Admin updatedBy;
	
	
	private LocalDateTime updatedAt;

}
