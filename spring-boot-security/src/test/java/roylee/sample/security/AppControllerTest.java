package roylee.sample.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import roylee.sample.security.config.AppConfig;
import roylee.sample.security.controller.AppController;

@DisplayName("로그인 테스트 - @WebMvcTest")
@WebMvcTest(AppController.class)
@Import({ AppConfig.class })
class AppControllerTest {

	@Autowired
	private MockMvc mockMvc;

	BasicErrorController a;

	@Autowired
	private PasswordEncoder passwordEncoder;

	UserDetails user1() {
		// @formatter:off
		return User.builder()
				   .username("test121212")
				   .password(passwordEncoder.encode("pwd1234"))
				   .roles("USER")
				   .build();
	}

	@DisplayName("main 페이지 - 로그인하지 않은 경우")
	@Test
	void test_login_unauthorized_main() throws Exception {
		// @formatter:off
		mockMvc.perform(get("/main"))
			   .andExpect(status().is3xxRedirection())
			   .andReturn()
			   .getResponse()
			   .getContentAsString();
		// @formatter:on
	}

	@DisplayName("main 페이지 - ROLE_USER 로그인 후 접속")
	@Test
	void test_login_user_main_page() throws Exception {
		// @formatter:off
		mockMvc.perform(get("/main").with( user(user1()) ) )
			   .andExpect(status().isOk())
			   .andReturn()
			   .getResponse()
			   .getContentAsString();
		// @formatter:on
	}
	
	@DisplayName("main 페이지 - ROLE_ADMIN 로그인 후 접속")
	@Test
	@WithMockUser(username = "admin", roles = ("ADMIN"))
	void test_login_admin_main_page() throws Exception {
		// @formatter:off
		mockMvc.perform(get("/main"))
			   .andExpect(status().isOk())
			   .andReturn()
			   .getResponse()
			   .getContentAsString();
		// @formatter:on
	}

	@DisplayName("admin 페이지 - 로그인 후 접속 - @WithMockUser 사용")
	@Test
	@WithMockUser(username = "admin", roles = ("ADMIN"))
	void test_login_admin_admin_page() throws Exception {
		// @formatter:off
		mockMvc.perform(get("/admin"))
			   .andExpect(status().isOk())
			   .andReturn()
			   .getResponse()
			   .getContentAsString();
		// @formatter:on
	}

	@DisplayName("admin 페이지 - 권한 없는 사용자 접근")
	@Test
	void test_login_unauthorized_admin() throws Exception {
		// @formatter:off
		mockMvc.perform(get("/admin").with( user(user1()) ) )
			   .andExpect(status().is4xxClientError())
			   .andDo(print())
			   .andReturn()
			   .getResponse()
			   .getContentAsString();
		// @formatter:on
	}

}
