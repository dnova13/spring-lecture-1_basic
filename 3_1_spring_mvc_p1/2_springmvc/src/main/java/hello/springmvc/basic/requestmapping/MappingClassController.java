package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
public class MappingClassController {

    /**
     * 회원 목록 조회 : GET /mapping/users
     * 회원 등록 : POST /mapping/users
     * 회원 조회 : GET /mapping/users/{userId}
     * 회원 수정 : PATCH /mapping/users/{userId}
     * 회원 삭제 : DELETE /mapping/users/{userId}
     */

    @GetMapping("/mapping/users")
    public String user() {
        return "get users";
    }

    @PostMapping("/mapping/users")
    public String addUser() {
        return "post user";
    }

    @GetMapping("/mapping/users/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/mapping/users/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    @DeleteMapping("/mapping/users/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }
}