package roylee.sample.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roylee.sample.security.repository.UserRepository;
import roylee.sample.security.vo.UserEx;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final TestService testService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// @formatter:off
        return userRepository.findOneByUserId(username)
                               .map(UserEx::new)
                               .orElseThrow(() -> new UsernameNotFoundException(String.format("Can not found User : %s", username)) );
        // @formatter:on
	}

}
