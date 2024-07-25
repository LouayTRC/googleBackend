package club_website.auth.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Application")
public class Application {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	private String fullname;
	private String mail;
	private String depart;
	private String facDepart;
	private String reason;
	private String skills;
	private String clubsExperience;
	
	@Builder.Default
	private Integer status=0;
	
	@ManyToOne()
    @JoinColumn(name = "update_by_id")
	private Admin updatedBy;
	
	private LocalDate updatedAt;
	
	
	

}
