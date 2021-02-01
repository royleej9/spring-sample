# 기본 구성 
- STS 템플릿 사용
- project > Spring > Spring Legacy Project > Spring MVC Project

# 이클립스 - 환경 설정
- Properties > Java Compiler > 시스템에 설치된 자바 버전 선택
- Properties > Java Build Path > 시스템에 설치된 자바 버전 선택
- Properties > Project Facts > 시스템에 설치된 자바 버전 선택
- Perferences > encoding > css/html/jsp...기타 등. UTF-8로 변경

## jsp - 한글 깨짐
- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

## pom.xml 아래와 같은 에러 발생시
```
- The container 'Maven Dependencies.......
- invalid LOC header
```
- C:\Users\계정\.m2 폴더 삭제
- pom.xml > run as > Maven Clean
- Project > Clean

# 설정
## web.xml
- 루트 애플리케이션 컨텍스트 등록
- 서비스 계층, 데이터 계층 등을 정의
``` xml
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>/WEB-INF/spring/root-context.xml</param-value>
</context-param>
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

## 서블릿 애플리케이션 컨텍스트 등록
``` xml
<servlet>
	<servlet-name>appServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
```

## root-context.xml
- 서비스 계층, 데이터 계층 등을 정의

## servlet-context.xml
- 서블릿 관련 정의 - controller, json 등...


# junit
## WebAppConfiguration / ContextConfiguration
``` java
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class TestHomeController {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}

	@Test
	public void tsetController() throws Exception {
		this.mockMvc.perform(get("/"))
					.andExpect(status().isOk()).andDo(print());
	}
}
```

## MockitoJUnitRunner
``` java
@RunWith(MockitoJUnitRunner.class)
public class TestHomeControllerMockito {
	
	@InjectMocks
	private HomeController homeController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void tsetController() throws Exception {
		this.mockMvc.perform(get("/"))
					.andExpect(status().isOk()).andDo(print());
	}
}

```
참고 - 토비의 스프링 10장 IOC 컨테이너와 DI