package royleej9.junit.web.user;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserComponent {

    public UserComponent() {
        log.info("INIT UserComponent============================================");
    }
}
