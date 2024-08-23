package club_website.auth.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 2968060L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    
    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String mail;
    
    private String password;
    private String pdp;
 
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    

    @OneToOne(mappedBy ="user")
    @Nullable
    @JsonIgnoreProperties("user")
    private Member member;
    
    @OneToOne(mappedBy ="user")
    @Nullable
    @JsonIgnoreProperties({"createdBy","user"})
    private Admin admin;
    
    
    
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = this.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
    
    @JsonProperty("roles")
    private Set<Role> getRoles() {
        return roles;
    }


    public boolean hasAuthority(String authority) {
    	return getRoles().stream().map(Role::getName).anyMatch(roleName -> roleName.equals(authority));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
    
}
