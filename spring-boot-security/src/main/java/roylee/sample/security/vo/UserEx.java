package roylee.sample.security.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import roylee.sample.security.entity.Role;
import roylee.sample.security.entity.User;

public class UserEx implements UserDetails, CredentialsContainer {
	private static final long serialVersionUID = -8642496986710847019L;

	private final User user;
	private final Collection<GrantedAuthority> authorities;
	private String password;

	public UserEx(User user) {
		this.password = user.getPassword();
		this.user = user.toBuilder().password(null).build();
		this.authorities = Collections.unmodifiableCollection(convertAuthorities(user.getRoles()));
	}

	/**
	 * 인증 후 중요 정보 null
	 */
	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.user.getUserId();
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
		return true;
	}

	@Override
	public int hashCode() {
		return this.getUsername().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEx other = (UserEx) obj;
		return Objects.equals(user.getUserId(), other.user.getUserId());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName()).append(" [");
		sb.append("Username=").append(this.user.getUserId()).append(", ");
		sb.append("Password=[PROTECTED], ");
		sb.append("Enabled=").append(this.isEnabled()).append(", ");
		sb.append("AccountNonExpired=").append(this.isAccountNonExpired()).append(", ");
		sb.append("credentialsNonExpired=").append(this.isCredentialsNonExpired()).append(", ");
		sb.append("AccountNonLocked=").append(this.isAccountNonLocked()).append(", ");
		sb.append("Granted Authorities=").append(this.authorities).append("]");
		return sb.toString();
	}

	private Collection<? extends GrantedAuthority> convertAuthorities(Collection<Role> roles) {
		return roles.stream().map(m -> new SimpleGrantedAuthority(m.getName()))
				.collect(Collectors.toCollection(ArrayList::new));
	}
//	
//	// org.springframework.security.core.userdetails.User
//	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
//		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
//		// Ensure array iteration order is predictable (as per
//		// UserDetails.getAuthorities() contract and SEC-717)
//		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
//		for (GrantedAuthority grantedAuthority : authorities) {
//			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
//			sortedAuthorities.add(grantedAuthority);
//		}
//		return sortedAuthorities;
//	}

}
