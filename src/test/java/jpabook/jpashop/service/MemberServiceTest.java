package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Autowired
    EntityManager em;
    @Test
    public void 테스트_회원_생성1() throws Exception{
        //given
        Address address = new Address("대구","명덕로","21353");
        Member member = new Member();
        member.setName("김현우");
        member.setAddress(address);

        //when
        Long savedId = memberService.join(member);
        //then
        em.flush();
        assertEquals(member,memberRepository.findOne(savedId));
    }
    @Test
    public void 테스트_회원_생성2() throws Exception{
        //given
        Address address = new Address("대구","청수로","55353");
        Member member = new Member();
        member.setName("김현명");
        member.setAddress(address);

        //when
        Long savedId = memberService.join(member);
        //then
        em.flush();
        assertEquals(member,memberRepository.findOne(savedId));
    }
    @Test
    public void 테스트_회원_생성3() throws Exception{
        //given
        Address address = new Address("대구","범물로","11353");
        Member member = new Member();
        member.setName("신운홍");
        member.setAddress(address);

        //when
        Long savedId = memberService.join(member);
        //then
        em.flush();
        assertEquals(member,memberRepository.findOne(savedId));
    }
    @Test
    public void 테스트_회원_생성4() throws Exception{
        //given
        Address address = new Address("경산","사동로","66353");
        Member member = new Member();
        member.setName("정재욱");
        member.setAddress(address);

        //when
        Long savedId = memberService.join(member);
        //then
        em.flush();
        assertEquals(member,memberRepository.findOne(savedId));
    }
/*    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야한다.

        //then
        fail("예외가 발생해야 한다.");
    }*/

}