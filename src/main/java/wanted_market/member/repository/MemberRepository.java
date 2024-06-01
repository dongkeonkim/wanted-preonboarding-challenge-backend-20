package wanted_market.member.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import wanted_market.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Id> {

    Optional<Member> findByMemberId(Long memberId);

}
