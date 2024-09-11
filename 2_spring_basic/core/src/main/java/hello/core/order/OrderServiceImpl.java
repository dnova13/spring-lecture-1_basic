package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원

    // dip, ocp 위반
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 가격
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 인터페이스에만 의존하도록 아래와 같이 변경
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    // 수정자 주입 예시를 위해 이전 코드 final 지움.
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
    
    // 필드 주입에 대한 예시로 인해 위의 코드 주석처리
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 생성자 생성하여 의존성 주입
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    
/*
   // ****** 필드 주입 예시를 위한 주석처리

    // 수정자 주입 예시
    // 주입할 대상이 없어도 동작하게 하려면 (required = false) 설정
    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    // 수정자 주입 예시
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("DiscountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/


    /* 예시용으로 더이상 안쓰니 주석처리
    
    // 필드 주입 설명을 위해 setter 추가
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 필드 주입 설명을 위해 setter 추가
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {


        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}