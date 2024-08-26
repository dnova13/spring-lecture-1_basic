package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /**
     * spring jpa 즉 spring 에서 제공하는 jpa를 도와주려고 만든 기능을 이용하면
     * 아래와 같이 spring jpa 메소드에서 overriding 하여
     * JPQL select m from Member m where m.name = ?
     * 으로 하여 위의 쿼리 내용을 찾아줌.
     * */
    @Override
    Optional<Member> findByName(String name);
}