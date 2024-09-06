package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //  MemberService를 MemberServiceImpl로 쓸거야
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할을 주기 위해 리팩토링 추가
    // MemberRepository는 MemoryMemberRepository로 쓸거야 라고 추가
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // OrderService는 나의 애플리케이션에서 결정한 memberRepository를 가져오고 discountPolicy를 가져올거야
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // DiscountPolicy 역할을 주기위해 리팩토링 코드 추가
    // DiscountPolicy는  FixDiscountPolicy 쓸거야 라고 리팩토링 추가
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();

        // AppConfig를 통해 DiscountPolicy 를 RateDiscountPolicy 로 변경
        return new RateDiscountPolicy();

    }
}