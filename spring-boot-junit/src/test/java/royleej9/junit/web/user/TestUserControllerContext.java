package royleej9.junit.web.user;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import royleej9.junit.web.user.config.TestUserConfig;

/*-
 * @SpringBootTest /@AutoConfigureMockMvc 
 * - 사용하기 간단한 테스트 방법
 * - @ContextConfiguration을 사용하여 환경 설정을 별도 java 파일로 정의
 * - ComponentScan을 사용하여 스캔 범위 지정
 * 
 * @author royleej9
 * @param <O>
 *
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestUserConfig.class)
public class TestUserControllerContext {
    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    // @formatter:off
    final User user1 = User.builder()
                           .id("id1")
                           .name("test1")
                           .password("pwd123")
                           .createdDate(new Timestamp(System.currentTimeMillis()))
                           .build();
    // @formatter:on

    @BeforeEach
    public void setup() throws Exception {
        String param = objectMapper.writeValueAsString(user1);

        // @formatter:off
        this.mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON).content(param))
                    .andExpect(status().isOk())
                    .andDo(print());
        // @formatter:on
    }

    @Test
    public void testGetUsers() throws Exception {
        // when // then
        // @formatter:off
        this.mockMvc.perform(get("/users")
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andDo(print());
    }

    @Test
    public void testGetUsersResult() throws Exception {
        // when // then
        // @formatter:off
        MvcResult result = this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                                       .andExpect(status().isOk())
                                       .andExpect(jsonPath("$", hasSize(1)))
                                       .andDo(print()).andReturn();

        String stringResult = result.getResponse().getContentAsString();
        log.info(stringResult);

//        String param = objectMapper.writeValueAsString(users);
//        boolean doesContain = stringResult.contains(param);
//        assertTrue(doesContain);
    }
}