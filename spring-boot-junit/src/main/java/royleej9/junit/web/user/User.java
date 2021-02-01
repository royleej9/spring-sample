package royleej9.junit.web.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private long seq;
    private String id;
    private String password;
    private String name;
    private String group;
    private Timestamp createdDate;
    private Timestamp updatedDate;

}