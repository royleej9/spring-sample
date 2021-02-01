package royleej9.junit.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public UserService() {
        log.info("INIT UserService=====================================================");
    }

    public boolean insertUser(final User user) {
        final User conUser = User.builder().id(user.getId()).build();
        if (getUsers(conUser).size() == 0) {
            return userMapper.insertUser(user) != 0 ? true : false;
        }
        return false;
    }

    public List<User> getUsers(final User user) {
        return userMapper.getUsers(user);
    }

//    public int insertUserTransaction(final User user) {
//        // normal
//        userMapper.insertUser(user);
//
//        // exception
//        final User cUser = user.toBuilder().id("").build();
//        userMapper.insertUser(cUser);
//        return 0;
//    }
//
    public boolean updateUser(final User user) {
        return userMapper.updateUser(user) != 0 ? true : false;
    }

//
    public boolean deleteUser(final String id) {
        return userMapper.deleteUser(id) != 0 ? true : false;
    }
}
