package hello.core;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.springframework.context.annotation.ComponentScan.*;



/* *
 * @ComponentScan : @Component가 붙은 클래스를 찾아서 자동으로 스프링 빈으로 등록
 *
 * excludeFilters :
 * - AutoAppConfig를 사용하기 때문에 AppConfig는 무시하도록 필터
 * - AppConfig에도 @Component가 있기 때문에 자동으로 스프링 빈에 등록되어 진다.
 *    그래서 필터를 사용하여 @configuration 어노테이션 붙여있는 코드는  무시
 *
 * basePackages : 탐색할 패키지의 시작 위치를 지정한다.
 *              이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
 *
 * */
@Configuration
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes =
                Configuration.class))
public class AutoAppConfig {

}