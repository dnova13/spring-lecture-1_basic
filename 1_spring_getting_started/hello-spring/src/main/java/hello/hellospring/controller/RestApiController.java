package hello.hellospring.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class RestApiController {

    @Operation(summary = "Hello World string API", description = "간단한 Hello World 메시지를 반환하는 string API")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }


    // rest api
    @Operation(summary = "Hello World json API", description = "간단한 Hello World 메시지를 반환하는 json API")
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    public class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
