package jpabook.jpashop.service;

import jakarta.transaction.Transactional;
import java.util.List;
import jpabook.jpashop.entity.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  @Autowired
  private final MemberRepository memberRepository;


  // 회원가입
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if (! findMembers.isEmpty()) {
      throw new IllegalArgumentException("이미 존재하는 회원입니다.");
    }
  }

  // 회원 전체 조회
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  // 회원 단건 조회
  public Member findOne(Long memberId) {
    return memberRepository.findOne(memberId);
  }
}
