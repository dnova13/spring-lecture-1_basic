package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // tip! psvm 치면 자동생성.
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        // 1L long 타입이라서 숫자 뒤에 L 을 붙임, 소문자 l은 가능하나 대문자 권장.
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);


        // tip! ctrl + v
        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
        /*
        * output :
        *
        * new member = memberA
        * find Member = memberA
        * */

    }
}