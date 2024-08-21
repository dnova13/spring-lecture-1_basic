package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;

// java 8 에서 지원하는 기능,
// 다른 언어와 달리 java 에서 변수에 null 등 예상치 못한 값이 왔을때 이러한 에러에 대해 유연하지 않고 코드 복장성이 있는데
// 해당 기능을 사용해서 그나마 유연하게 대처하게 만들어줌
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}