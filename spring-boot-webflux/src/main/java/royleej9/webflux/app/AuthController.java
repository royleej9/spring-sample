package royleej9.webflux.app;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AuthController {

    @PostMapping(value = "/auth/login")
    public Mono<String> login(ServerWebExchange exchange) {    
        return exchange.getFormData()
                .flatMap(formData -> {
                  String id = formData.getFirst("id");
                  String password = formData.getFirst("password");
                  log.info("{} : {}", id, password);
                  return Mono.empty().thenReturn("redirect:/main");
                });
        
//        Mono<MultiValueMap<String, String>> data = exchange.getFormData();
//        return data.map(formData -> {
//            String id = formData.getFirst("id");
//            String password = formData.getFirst("password");
//            return Mono.just("Just Hello");
//        }).thenReturn("redirect:/main");
//        return data.thenReturn("redirect:/main");
//        return Mono.empty().thenReturn("redirect:/main");
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
