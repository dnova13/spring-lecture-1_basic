package hello.hello_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 해당 어노테이션 톰캣 서버 내장된채로 프로젝트 실행 되게 해준다.
@SpringBootApplication
public class HelloSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);

	}

}
