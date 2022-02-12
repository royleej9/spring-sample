package royleej9.junit.web.tarzan;

import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TarzanController {

    public TarzanController() {
        log.info("INIT TarzanController==============================================================");
        val count = 100000;
        for (var i = 0; i < count; i++) {
            log.info("타잔이" + i * 10 + "원 짜리 팬티를 입고" + i * 20 + "원 짜리 칼을 찾고 노래를 한다.");
        }
    }
}
