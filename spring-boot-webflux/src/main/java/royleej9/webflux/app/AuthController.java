package royleej9.webflux.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AuthController {

    @PostMapping(value = "/auth/login")
    public Mono<String> login(ServerWebExchange exchange) {
        return exchange.getFormData().flatMap(formData -> {
            String id = formData.getFirst("id");
            String password = formData.getFirst("password");
            log.info("{} : {}", id, password);

//            String user = findUserById(id)
//            if (user.isEmpty() ) {
//                return Mono.empty().thenReturn("redirect:/?error");
//            } else {
//                return Mono.empty().thenReturn("redirect:/main");
//            }
//            
            return findUserById(id)
                    .map(x -> "redirect:/main")
                    .switchIfEmpty(Mono.just("redirect:/?error"));
        }).log();
    }

    private Mono<String> findUserById(String id) {
        log.info("findUserById");
        return id.equals("admin") ? Mono.just("admin") : Mono.empty();
    }

//    @PostMapping(value = "/auth/login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
//    public Mono<ResponseEntity<String>> login(ServerWebExchange exchange) {
//        Mono<MultiValueMap<String, String>> data = exchange.getFormData();
//        
//        return Mono.just(new HttpHeaders())
//                .doOnNext(header -> header.add("Location", "/?error"))
//                .map(header -> new ResponseEntity<>(null, header, HttpStatus.MOVED_PERMANENTLY));
//    }
}
