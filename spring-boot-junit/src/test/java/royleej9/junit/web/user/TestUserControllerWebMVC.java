package royleej9.junit.web.user;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*-
 * controller Test
 * Mock을 사용하여 Service의 리턴 값을 가정하여 
 * controller의 url 테스트 
 * spring mvc 관련 부분 테스트 할때 사용 
 *  - @Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, WebMvcConfigurer, and HandlerMethodArgumentResolver
 * @Component 등 어노테이션은 자동 스캔 되지 않음 (Mock등을 사용해야함)
 * 
 * 참고: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests
 * 
 * @author royleej9
 *
 */
@Slf4j
@SpringBootTest
@WebMvcTest(UserController.class)
public class TestUserControllerWebMVC {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    final User user1 = User.builder()
            .id("id1")
            .name("test1")
            .password("pwd123")
            .createdDate(new Timestamp(System.currentTimeMillis()))
            .build();

    final User user2 = User.builder()
            .id("id2")
            .name("test2")
            .password("pwd321")
            .createdDate(new Timestamp(System.currentTimeMillis()))
            .build();

    @BeforeEach
    public void setup() throws Exception {
    }

    @Test
    public void testGetUsers() throws Exception {
        // given
        when(userService.getUsers(null)).thenReturn(Arrays.asList(user1, user2));

        // when // then
        this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    public void testGetUsersResult() throws Exception {
        List<User> users = Arrays.asList(user1, user2);

        // given
        given(userService.getUsers(null)).willReturn(users);

        // when // then
        MvcResult result = this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print())
                .andReturn();

        String stringResult = result.getResponse().getContentAsString();
        log.info(stringResult);

        String param = objectMapper.writeValueAsString(users);
        boolean doesContain = stringResult.contains(param);
        assertTrue(doesContain);
    }
}
