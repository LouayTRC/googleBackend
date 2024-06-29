package club_website.auth.Models;

import lombok.*;

import java.io.Serializable;

import jakarta.persistence.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role implements Serializable{
	
	private static final long serialVersionUID = 29060L;
	
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    
    
    
    
}