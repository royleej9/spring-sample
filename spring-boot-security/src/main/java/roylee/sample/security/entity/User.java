package roylee.sample.security.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

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
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private long userNo;

	@Column(unique = true, nullable = false)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Email
	private String email;

	@Builder.Default
	@Column(nullable = false)
	private boolean enabled = true;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime created;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updated;
	
	@ManyToMany
	@JoinTable(
            name = "users_roles", 
            joinColumns = @JoinColumn(
              name = "user_no", referencedColumnName = "userNo"), 
            inverseJoinColumns = @JoinColumn(
              name = "role_id", referencedColumnName = "id")
			)
	private Collection<Role> roles;
}