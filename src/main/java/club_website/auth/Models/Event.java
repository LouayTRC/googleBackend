package club_website.auth.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Event")
@SQLDelete(sql = "UPDATE Event SET deleted = true WHERE id=?")
@FilterDef(name = "deletedEventsFilter", parameters = @ParamDef(name = "isDeleted", type = boolean.class))
@Filter(name = "deletedEventsFilter", condition = "deleted = :isDeleted")
public class Event implements Serializable{
	
	private static final long serialVersionUID = 2913260L;
	
	@Id
	@Column(name = "event_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	private String description;
	private String place;
	private String pic;

	private LocalDateTime dateEvent;
	
	@Builder.Default
	private boolean deleted = Boolean.FALSE;
	
	@ManyToOne()
    @JoinColumn(name = "created_by_id")
	@JsonIgnoreProperties({"member"})
	private Admin createdBy;
	

	private LocalDate createdAt;
	
	@ManyToOne()
    @JoinColumn(name = "deleted_by_id")
	@JsonIgnoreProperties({"works","departments","member"})
	private Admin deletedBy;
	

	private LocalDate deletedAt;
	
	@ManyToOne()
    @JoinColumn(name = "update_by_id")
	private Admin updatedBy;
	

	private LocalDate updatedAt;
	
	@Builder.Default
	private boolean status=true;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "events_departments",
			joinColumns = @JoinColumn(name = "event_id"),
			inverseJoinColumns = @JoinColumn(name = "department_id")
	)
	@JsonIgnoreProperties("tasks")
	private Set<Department> departments=new HashSet<>();
	
	
	@OneToMany(mappedBy = "event")
	@JsonIgnore
	private List<MemberEvent> presenceList;
	
}
