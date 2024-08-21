package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끌날 경우 메모리에 저장한 데이터를 다 지운다.
    // 실제 개발용 테스트는 이래도 된다.
    // 실제 db 에서 이뤄지지 않으므로.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + result);

//        Assertions.assertEquals(member, null);

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        // 예제를 위해 일부러 spring1 -> spring2 로 찾게하여 에러냄
        Member result = repository.findByName("spring2").get();
        System.out.println("result = " + result);

        //then
        // 예시를 위해 실패 뜸 member1, member2를 비교했긴 때문에
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        System.out.println("result = " + result);

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}