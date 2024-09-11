package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // 롬북 적용
public class MemberServiceImpl implements MemberService {
    
//    dip ocp 위반
//    private final MemberRepository memberRepository = new
//            MemoryMemberRepository();

    // MemberRepository 인터페이스만 의존
    // MemoryMemberRepository 의존하지 않게 변수에 값을 넣지 않음.
    // 롬북 적용 후 필드에 final이 붙은거만 모아서 자동으로 만들어줌.
    private final MemberRepository memberRepository;
    
    // 그래서 생정자 생성
    // 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다
    // lombok 에 의한 자동 생성자 주입으로 인한 생성자 주입 주석처리
    /*@Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}