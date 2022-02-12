package royleej9.junit.web.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

/*-
 * @author royleej9
 *
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
class TestUserServiceMock {

    // https://www.baeldung.com/mockito-annotations
    @Mock
    private UserMapper userMapper;

//    @Spy
    @InjectMocks
    private UserService userService;

    final User user1 = User.builder().id("id1").name("test1").password("pwd123")
            .createdDate(new Timestamp(System.currentTimeMillis())).build();

    final User user2 = User.builder().id("id2").name("test2").password("pwd321")
            .createdDate(new Timestamp(System.currentTimeMillis())).build();

    final User user3 = User.builder().id("id3").name("test3").password("pwd321")
            .createdDate(new Timestamp(System.currentTimeMillis())).build();

    @BeforeEach
    public void before() {
        // class 위에 @RunWith(MockitoJUnitRunner.class) 가 선언이 안된 경우 추가해야함
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserInfo1() throws Exception {
        log.info("testGetUserInfo1");

        doReturn(Arrays.asList(user1)).when(userMapper).getUsers(user1);

        List<User> users = userService.getUsers(user1);
        assertThat(users.size(), is(1));
        assertTrue(users.get(0).getId().equals(user1.getId()));
    }

    @Test
    void testGetUserAll() throws Exception {
        log.info("testGetUserAll");

        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);

        when(userMapper.getUsers(any(User.class))).thenReturn(users);
        List<User> result = userService.getUsers(user1);
        assertThat(result.size(), is(2));
    }

    @Test
    void testInsertUser() throws Exception {
        log.info("testInsertUser");

        // userMapper
        doReturn(new ArrayList<User>()).when(userMapper).getUsers(any(User.class));
        doReturn(1).when(userMapper).insertUser(any(User.class));

        // userService
//        doReturn(new ArrayList<User>()).when(userService).getUsers(any(User.class));

        final boolean isAdded = userService.insertUser(user3);
        assertTrue(isAdded);
        verify(userMapper).insertUser(user3);
    }

    @Test
    void testInsertUserDupli() throws Exception {
        log.info("testInsertUserDupli");
        doReturn(Arrays.asList(user3)).when(userMapper).getUsers(any(User.class));
        final boolean isAdded = userService.insertUser(user3);
        assertFalse(isAdded);
    }

}
