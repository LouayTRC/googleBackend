package club_website.auth.Models;

import java.io.Serializable;

import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="scores")
public class MonthScore implements Serializable{
	
	private static final long serialVersionUID = 8061450L;
	
	
 	@Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer score_id;
 	
 	@ManyToOne
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
}
