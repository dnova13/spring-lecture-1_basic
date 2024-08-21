package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    /**
     * 동시성 문제가 고려되어 있지 않음,
     * 그래서 HashMap 보다는
     * 실무에서는 ConcurrentHashMap,AtomicLong
     */

    // 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap 사용 권장
    private static Map<Long, Member> store = new HashMap<>();

    // 동시성 문제가 고려되어 있지 않음, 실무에서는 AtomicLong 사용 권장
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // 이런식으로 확실히 동시성 문제가 있음.
        // 보통은 이런 시퀀스 처리는 db 에서 처리하도록 하던가
        // uuid를 통해 지정
        // java 같은 경우 ConcurrentHashMap 해결 되나 보네.
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}