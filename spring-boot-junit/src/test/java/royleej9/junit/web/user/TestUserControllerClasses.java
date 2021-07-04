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
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*-
 * @SpringBootTest /@AutoConfigureMockMvc 
 * 테스트에 필요한 class를 지정함 / 전체를 스캔하지 않음
 * 
 * @author royleej9
 * @param <O>
 *
 */
@Slf4j
@MapperScan("royleej9.junit.web.user")
@AutoConfigureMybatis
@EnableAutoConfiguration
@SpringBootTest(classes= {UserController.class, UserService.class})
@AutoConfigureMockMvc
public class TestUserControllerClasses {
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
        this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
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
                                       .andDo(print())
                                       .andReturn();

        String stringResult = result.getResponse().getContentAsString();
        log.info(stringResult);

//        String param = objectMapper.writeValueAsString(users);
//        boolean doesContain = stringResult.contains(param);
//        assertTrue(doesContain);
    }
}