package roylee.sample.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import roylee.sample.security.entity.Role;
import roylee.sample.security.entity.User;
import roylee.sample.security.repository.RoleRepository;

@DisplayName("UserDetailsServiceEx - EntityManager")
@DataJpaTest
@ActiveProfiles("test")
@Import( value = {UserService.class, TestService.class})
class UserServiceTest {

    @Autowired
    TestEntityManager testEntityManager;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserService userService;
    
    @Autowired
    TestService testService;
    
	@BeforeEach
	void init() {
		var userRole = Role.builder()
				.name("ROLE_USER")
				.description("사용자")
				.build();
		testEntityManager.persist(userRole);
		
		var adminRole = Role.builder()
				.name("ROLE_ADMIN")
				.description("관리자")
				.build();
		testEntityManager.persist(adminRole);
		
		var user = User.builder()
				.userId("admin")
				.name("관리자")
				.password("pwd1234")
				.email("admin@test.net")
				.roles(Arrays.asList(roleRepository.findOneByName("ROLE_ADMIN")))
				.build();
		testEntityManager.persist(user);
	}
	
	@DisplayName("admin 조회 테스트")
	@Test
	void test_1() {
		
		// when
		UserDetails user = userService.loadUserByUsername("admin");
		
		// then
		assertEquals("admin", user.getUsername());
		assertEquals(1, user.getAuthorities().size());
	}
	
	@DisplayName("등록되지 않은 사용자 조회")
	@Test
	void test_2() {
		assertThrows(UsernameNotFoundException.class, () -> {
			userService.loadUserByUsername("unknownUser");			
		});		
	}
}
