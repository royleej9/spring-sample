import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.royleej9.sample.web.HomeController;

@RunWith(MockitoJUnitRunner.class)
public class TestController {

	@InjectMocks
	private HomeController homeController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void tsetController() throws Exception {
		this.mockMvc.perform(get("/"))
					.andExpect(status().isOk())
					.andDo(print());
	}

	@Test
	public void testGetController() throws Exception {
		String id = "testUser";
		String pwd = "testPwd";

		this.mockMvc.perform(get("/ajax/testGet?id={id}&pwd={pwd}", id, pwd))
					.andExpect(status().isOk())
					.andExpect(jsonPath("id", is(id)))
					.andExpect(jsonPath("pwd", is(pwd)))
					.andDo(print());
	}

	@Test
	public void tsetUnknownUrl() throws Exception {
		this.mockMvc.perform(get("/unknown"))
					.andExpect(status().is4xxClientError())
					.andDo(print());
	}
}
