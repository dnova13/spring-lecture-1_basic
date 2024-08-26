package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;



// 서비스는 비지니스에 맞도록 의존적으로 개발.
// jpa 사용시 JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
// @Transactional 설정해야된다.
@Transactional
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 리포지토리의 코드가
     * 회원 서비스 코드를 DI 가능하게 변경한다.
     */
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증 private 사용.
    private void validateDuplicateMember(Member member) {
        // memberRepository.findByName 을 반환 객체가 Optional
        // Optional 기능 중 하나 인  ifPresent
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 하나 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}