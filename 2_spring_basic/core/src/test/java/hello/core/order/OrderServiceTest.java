package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void creatOrder() {
        Long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
        // basic 등급은 할인 0
        Member member = new Member(memberId, "memberA", Grade.BASIC);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // vip 등급일 경우
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        
        // basic 등급일 경우
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(0);
    }
}