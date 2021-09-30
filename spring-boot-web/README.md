# spring-boot

## 시작 - STS

1. New Spring Starter Project

### jsp 설정

 1. src/main/webapp/WEB-INF/jsp 폴더 생성
 2. application.properties

    ```properties
    spring.mvc.view.prefix = /WEB-INF/views/
    spring.mvc.view.suffix = .jsp
    ```

 3. pom.xml - jsp 관련 lib추가 

    ``` xml
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
    </dependency>

    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
        <scope>provided</scope>
    </dependency>
    ```