package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//회원가입
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final붙은 애들 생성자 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

    //constructor injection
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //중복된 회원이 있으면 예외 터뜨리깅
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }
    //회원 전체 조회

    public List<Member>findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}

