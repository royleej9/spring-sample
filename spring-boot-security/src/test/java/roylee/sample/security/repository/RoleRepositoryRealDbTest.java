package roylee.sample.security.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import roylee.sample.security.entity.Role;

@DisplayName("Role 테스트 - Real db")
@Rollback(false)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RoleRepositoryRealDbTest {
	// @formatter:off	
	@Autowired
	RoleRepository roleRepository;
	
	@DisplayName("Role 등록/조회 테스트")
	@Test
	void test_1() {
		// given
		Role role = Role.builder()
						.name("ROLE_USER")
						.description("사용자")
						.build();
		// when/then
		assertNotNull(roleRepository.save(role), "Role 등록 테스트");
		assertNotNull(roleRepository.findOneByName("ROLE_USER"), "Role 검색 테스트");
	}
}
