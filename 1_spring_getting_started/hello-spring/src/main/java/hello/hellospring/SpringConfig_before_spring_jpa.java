/*
package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class SpringConfig_before_spring_jpa {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig_before_spring_jpa(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    */
/**
     * memberService 스프링 빈에 등록
     * <p>
     * public MemberService(MemberRepository memberRepository) 에서
     * 여기서 등록된 스프링 빈을 위의 메소드 MemberService 넣어줌
     *//*

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    */
/**
     * memberRepository 스프링 빈에 등록
     * <p>
     * 등록된 스프링 빈을 MemoryMemberRepository 객체에 넣어줌.
     *//*

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplateMemberRepository 빈으로 하여 주입
        return new JpaMemberRepository(em);
    }
}



*/
