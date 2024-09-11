package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
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

    @Test
    void fieldInjectionTest() {

//        OrderServiceImpl orderService = new OrderServiceImpl();

      /*  orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.setDiscountPolicy(new FixDiscountPolicy());


        *//*
        *
        * 등록한 멤버가 없어서 에러가 발생한거지 orderServiceImpl 자체 에러는 없음
        * setter를 추가함으로써 2번의 수정자 주입과 같게 됨
        *
        * *//*
        orderService.createOrder(1L, "itemA", 10000);
*/

    }
}