package roylee.sample.security.event;

import java.util.Arrays;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import roylee.sample.security.entity.Role;
import roylee.sample.security.entity.User;
import roylee.sample.security.repository.RoleRepository;
import roylee.sample.security.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {
	
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("ContextRefreshedEvent - 초기화 작업");

		log.info("기본 Role 등록");
		createRole("ROLE_USER", "사용자");
		createRole("ROLE_ADMIN", "관리자");
		log.info("관리자 등록");
		createAdmin();
		log.info("테스터 등록");
		createTestUser();
	}
	
	private Role createRole(String name, String desc) {
		// @formatter:off
		var role = roleRepository.findOneByName(name);
		if (role == null) {
			role = Role.builder()
						.name(name)
						.description(desc)
						.build();
			roleRepository.save(role);
		}
		return role;
		// @formatter:on
	}
	
	private void createAdmin() {
		// @formatter:off
		var user = User.builder()
						.userId("admin")
						.name("관리자")
						.password(passwordEncoder.encode("1111") )
						.email("admin@test.net")
						.roles(Arrays.asList(roleRepository.findOneByName("ROLE_ADMIN")))
						.build();
		userRepository.save(user);
		// @formatter:on
	}

	private void createTestUser() {
		// @formatter:off
		var user = User.builder()
						.userId("test")
						.name("테스터")
						.password(passwordEncoder.encode("1111") )
						.email("test@test.net")
						.roles(Arrays.asList(roleRepository.findOneByName("ROLE_USER")))
						.build();
		userRepository.save(user);
		// @formatter:on
	}
}
