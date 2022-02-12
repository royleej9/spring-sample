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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*-
 * @SpringBootTest /@AutoConfigureMockMvc 
 * - 테스트 대상을 포함한 전체 bean을 스캔함- 시간이 많이 걸림
 * 
 * @author royleej9
 * @param <O>
 *
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class TestUserController {
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
    void testGetUsers() throws Exception {
        // when // then
        // @formatter:off
        this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andDo(print());
    }

    @Test
    void testGetUsersResult() throws Exception {
        // when // then
        // @formatter:off
        MvcResult result = this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                                       .andExpect(status().isOk())
                                       .andExpect(jsonPath("$", hasSize(1)))
                                       .andDo(print())
                                       .andReturn();

        String stringResult = result.getResponse().getContentAsString();
        log.info(stringResult);

//        String param = objectMapper.writeValueAsString(users);
//        boolean doesContain = stringResult.contains(param);
//        assertTrue(doesContain);
    }
}