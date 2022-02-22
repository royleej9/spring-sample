package roylee.sample.security.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
    private long id;
	
	private String name;
	
	private String description;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime created;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updated; 
	
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
