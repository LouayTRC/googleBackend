package club_website.auth.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="department")
public class Department implements Serializable {

    private static final long serialVersionUID = 2860L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    @JsonBackReference("user_department")
    private Set<User> users = new HashSet<>();
  
    @ManyToMany(mappedBy = "departmentss", fetch = FetchType.LAZY)
    @JsonBackReference("event_department")
    private Set<Event> events = new HashSet<>();
    
    
    @OneToMany(mappedBy = "department")
    @JsonManagedReference("task_dep")
    private List<Task> tasks = new ArrayList<>();

}
