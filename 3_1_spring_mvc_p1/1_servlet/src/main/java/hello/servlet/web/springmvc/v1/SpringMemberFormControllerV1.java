package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
* @Controller = @Component @RequestMapping
*
* **
*
* 주의! - 스프링 3.0 이상**
* 스프링 부트 3.0(스프링 프레임워크 6.0)부터는 클래스 레벨에 @RequestMapping이 있어도 스프링 컨트롤러로 인식하지 않는다.
* 오직 @Controller가 있어야 스프링 컨트롤러로 인식한다. 참고로 @RestController는 해당 애노테이션 내부에
* @Controller를 포함하고 있으므로 인식된다. 따라서 @Controller가 없는 위의 두 코드는 스프링 컨트롤러로 인식되지 않는다.
* (RequestMappingHandlerMapping에서 @RequestMapping는 이제 인식하지 않고, Controller만 인식한다.)
*
*
* */

@Controller
//@Component
//@RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form");
    }
}