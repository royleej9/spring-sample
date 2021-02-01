package royleej9.junit.web.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    List<User> getUsers(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(String id);
}