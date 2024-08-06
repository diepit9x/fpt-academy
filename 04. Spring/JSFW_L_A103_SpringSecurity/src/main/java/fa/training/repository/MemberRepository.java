package fa.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

	Optional<Member> findByEmail(String email);

}
