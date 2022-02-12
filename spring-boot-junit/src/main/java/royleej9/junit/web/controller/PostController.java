package royleej9.junit.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PostController {

    public PostController() {
        log.info("INIT PostController==================================");
    }

    @PostMapping(value = "/test-post")
    public Map<String, Object> testPost() {
        val result = new HashMap<String, Object>();
        result.put("id", "id-12");
        return result;
    }
}
