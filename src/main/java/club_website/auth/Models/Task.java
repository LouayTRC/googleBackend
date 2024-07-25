package club_website.auth.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Task")
@SQLDelete(sql = "UPDATE Task SET deleted = true WHERE id=?")
@FilterDef(name = "deletedTasksFilter", parameters = @ParamDef(name = "isDeleted", type = boolean.class))
@Filter(name = "deletedTasksFilter", condition = "deleted = :isDeleted")
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
    @JsonIgnoreProperties("tasks")
    private Department department;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonBackReference("task_work")
    @JsonIgnoreProperties("task")
    private List<Work> works = new ArrayList<>();

    @Builder.Default
	private boolean deleted = Boolean.FALSE;
	
	@ManyToOne()
    @JoinColumn(name = "created_by_id")
	private Admin createdBy;
	

	private LocalDate cratedAt;
	
	@ManyToOne()
    @JoinColumn(name = "deleted_by_id")
	private Admin deletedBy;
	

	private LocalDate deletedAt;
	
	@ManyToOne()
    @JoinColumn(name = "update_by_id")
	private Admin updatedBy;
	

	private LocalDate updatedAt;
	
}
