package roylee.sample.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import roylee.sample.security.entity.Role;
import roylee.sample.security.entity.User;
import roylee.sample.security.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserDetailsServiceEx implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// @formatter:off
        return userRepository.findOneByUserId(username)
                               .map(this::createUser )
                               .orElseThrow(() -> new UsernameNotFoundException("Can not found User") );
        // @formatter:on
	}

	private UserDetails createUser(User user) {
		// @formatter:off
    	return org.springframework.security.core.userdetails.User.builder()
    			.username(user.getUserId())
    			.password(user.getPassword())
    			.disabled(user.isEnabled())
    			.authorities(getAuthorities(user.getRoles()))
    			.build();
		// @formatter:on
    }

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return roles.stream()
			 .map(m -> new SimpleGrantedAuthority(m.getName()))
			 .collect(Collectors.toCollection(ArrayList::new));
	}
}
