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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    //  MemberService를 MemberServiceImpl로 쓸거야
    @Bean
    public MemberService memberService() {
        //1번
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할을 주기 위해 리팩토링 추가
    // MemberRepository는 MemoryMemberRepository로 쓸거야 라고 추가


    // OrderService는 나의 애플리케이션에서 결정한 memberRepository를 가져오고 discountPolicy를 가져올거야
    @Bean
    public OrderService orderService() {
        //1번
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        //2번? 3번?
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    // DiscountPolicy 역할을 주기위해 리팩토링 코드 추가
    // DiscountPolicy는  FixDiscountPolicy 쓸거야 라고 리팩토링 추가
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();

        // AppConfig를 통해 DiscountPolicy 를 RateDiscountPolicy 로 변경
        return new RateDiscountPolicy();

    }
}

