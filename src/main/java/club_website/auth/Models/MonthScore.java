package club_website.auth.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name="scores")
public class MonthScore implements Serializable{
	
	private static final long serialVersionUID = 8061450L;
	
	
 	@Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer score_id;
 	
 	@ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
 	@JoinColumn(name = "id_member")
 	private Member member;
 	
 	private String month;
 	private Integer year;
 	
 	private double departsPoints;
 	
 	@Builder.Default
	private double discipline=0;
 	
 	@Builder.Default
	private double contribution=0;
	
	private double score;
	
 	
	public double calculScore() {
		double total=departsPoints+discipline+contribution;
		return total!=0 ? total/3 : 0;
	}
	
	private LocalDateTime updatedAt;
	
	@ManyToOne()
	@JoinColumn(name = "update_by_id")
	private Admin updatedBy;
}
