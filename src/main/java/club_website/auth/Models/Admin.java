package club_website.auth.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name="Admin")
@ToString
public class Admin implements Serializable{
	
	private static final long serialVersionUID = 2060L;
	
	
	@Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admin_id;
	
	@OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonIgnoreProperties({"admin","createdBy"})
    private User user;
	
	private LocalDateTime createdAt;
	
//	@JsonIgnoreProperties({"createdBy"})
	@JsonIgnore
	@ManyToOne()
    @JoinColumn(name = "create_by_id")
	private Admin createdBy;
	
	
}
