package wanted_market.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted_market.member.domain.Member;
import wanted_market.member.dto.MemberDto;
import wanted_market.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Optional<MemberDto> findByMemberId(Long memberId) {
        return memberRepository.findByMemberId(memberId)
                .map(member -> {
                    MemberDto memberDto = new MemberDto();
                    memberDto.setMemberId(member.getMemberId());
                    return memberDto;
                });

    }

    @Transactional
    public void join(MemberDto memberDto) {
        Member member = Member.builder()
                .memberEmail(memberDto.getMemberEmail())
                .build();

        memberRepository.save(member);
    }

}
