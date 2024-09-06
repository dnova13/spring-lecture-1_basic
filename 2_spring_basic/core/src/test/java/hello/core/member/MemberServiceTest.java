package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
//        Member member = new Member(2L, "memberA", Grade.VIP);

        //when : join
        memberService.join(member);

        // 맴버를 찾음.
        Member findMember = memberService.findMember(1L);
//        Member findMember = memberService.findMember(2L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}