import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royleej9.sample.web.HomeController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class TestControllerJson {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}

	@Test
	public void testGet() throws Exception {
		String id = "testUser";
		String pwd = "testPwd";

		this.mockMvc.perform(get("/ajax/testGet?id={id}&pwd={pwd}", id, pwd))
						.andExpect(status().isOk())
						.andExpect(jsonPath("id", is(id)))
						.andExpect(jsonPath("pwd", is(pwd)))
						.andDo(print());
	}

	@Test
	public void testPostMap() throws Exception {
		String id = "testUser";
		String pwd = "testPwd";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("pwd", pwd);
		ObjectMapper mapper = new ObjectMapper();
		String param = mapper.writeValueAsString(paramMap);
		
		this.mockMvc.perform(post("/ajax/testPostMap")
				.contentType(MediaType.APPLICATION_JSON).content(param))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id", is(id)))
				.andExpect(jsonPath("pwd", is(pwd)))
				.andDo(print());
	}

}
