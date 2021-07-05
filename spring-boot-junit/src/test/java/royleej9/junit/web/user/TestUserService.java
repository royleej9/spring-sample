package royleej9.junit.web.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/*-
 * DB에서 데이터를 추가/조회를 하여 테스트 진행
 * @author royleej9
 * 프로젝트 전체를 스캔함
 *
 */

@Slf4j
@SpringBootTest
//@TestPropertySource("classpath:application-test.properties")
@Transactional
public class TestUserService {

    // https://stackoverflow.com/questions/6275785/wrapping-chained-method-calls-on-a-separate-line-in-eclipse-for-java

    @Autowired
    private UserService userService;

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

    final User user3 = User.builder()
            .id("id3")
            .name("test3")
            .password("pwd321")
            .createdDate(new Timestamp(System.currentTimeMillis()))
            .build();

    @BeforeEach
    public void before() throws Exception {
        log.info("before============");
        userService.insertUser(user1);
        userService.insertUser(user2);

        final List<User> users = userService.getUsers(new User());
        assertThat(users.size(), is(2));
    }

    @Test
    public void testGet() throws Exception {
        log.info("test getUsers");
        final User user = User.builder().build();
        final List<User> users = userService.getUsers(user);
        assertThat(users.size(), is(2));
    }

    @Test
    public void testGetById() throws Exception {
        log.info("test getUsers by id");
        final User user = User.builder().id("id1").build();
        final List<User> users = userService.getUsers(user);

        assertThat(users.size(), is(1));
    }

    @Test
    public void testInsert() throws Exception {
        final boolean isAdded1 = userService.insertUser(user1);
        assertFalse(isAdded1);

        final boolean isAdded2 = userService.insertUser(user2);
        assertFalse(isAdded2);

        final boolean isAdded = userService.insertUser(user3);
        assertTrue(isAdded);

        final List<User> users = userService.getUsers(user3);
        assertThat(users.size(), is(1));
    }

    @Test
    public void testDelete() {

        // when
        userService.deleteUser(user1.getId());

        // then
        final User user = User.builder().id(user1.getId()).build();
        final List<User> users = userService.getUsers(user);
        assertThat(users.size(), is(0));
    }

    @Test
    public void testUpdate() {

        // given
        final User updatedUser1 = User.builder()
                .id(user1.getId())
                .name("updateUser1")
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .build();

        final User updatedUser3 = User.builder()
                .id(user3.getId())
                .name("updateUser3")
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .build();

        // when
        final boolean isUpdatedUser1 = userService.updateUser(updatedUser1);
        final boolean isUpdatedUser3 = userService.updateUser(updatedUser3);

        // then
        assertTrue(isUpdatedUser1);
        assertFalse(isUpdatedUser3);

        final User user = User.builder().id(user1.getId()).build();
        final List<User> users = userService.getUsers(user);
        assertThat(users.size(), is(1));
        assertThat(updatedUser1.getUpdatedDate(), is(users.get(0).getUpdatedDate()));

    }
}
