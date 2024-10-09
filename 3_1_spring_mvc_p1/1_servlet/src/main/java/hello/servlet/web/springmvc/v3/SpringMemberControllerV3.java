package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Model 도입
 * ViewName 직접 변환
 * @RequestParam 사용
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 애노테이션 기반 컨트롤러는 ModelAndView를 반환하는것이 가능하고 문자 반환도 가능
    @GetMapping("/new-form")
    public String newForm() {
        System.out.println("SpringMemberControllerV3.newForm");

        return "new-form";
    }

    // request, response를 받을 수 있지만 파라미터를 직접 받을 수 있음
    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        System.out.println("SpringMemberControllerV3.save");

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        System.out.println("SpringMemberControllerV3.members");

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}