package club_website.auth.Models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Member implements Serializable,Comparable<Member>{
	
	private static final long serialVersionUID = 8060L;

	
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_id;
	
    @Builder.Default
	private double score=0.0;
    
	private Date birthday;
	private String cin;
	private String phone;
	
	@OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonIgnoreProperties("member")
    private User user;
	
	@Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "members_departments",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
	@JsonIgnoreProperties("tasks")
    private Set<Department> departments = new HashSet<>();
	
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Work> works;
    
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<MonthScore> monthScores;
    
    public double calculScore() {
    	if (monthScores.isEmpty()) {
    		return 0.0;
    	}
    	double sc=0;
    	for(MonthScore ms:monthScores) {
    		sc+=ms.getScore();
    	}
    	return sc;
    }
    
    @Override
    public int compareTo(Member other) {
        // Sorting in descending order by score
        return Double.compare(other.getScore(), this.getScore());
    }
}
