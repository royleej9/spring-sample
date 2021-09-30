package royleej9.junit.web.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("royleej9.junit.web.user")
@AutoConfigureMybatis
@EnableAutoConfiguration
@ComponentScan(basePackages = "royleej9.junit.web.user")
public class TestUserConfig {

}
