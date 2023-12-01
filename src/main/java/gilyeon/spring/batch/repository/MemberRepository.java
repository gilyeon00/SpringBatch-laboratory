package gilyeon.spring.batch.repository;

import gilyeon.spring.batch.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
