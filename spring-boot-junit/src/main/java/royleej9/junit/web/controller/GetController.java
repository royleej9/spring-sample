package royleej9.junit.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GetController {

    public GetController() {
        log.info("INIT GetController==================================");
    }

    @GetMapping(value = "/test-get")
    public Map<String, Object> testGet() {
        val result = new HashMap<String, Object>();
        result.put("id", "test123");

        return result;
    }
}
