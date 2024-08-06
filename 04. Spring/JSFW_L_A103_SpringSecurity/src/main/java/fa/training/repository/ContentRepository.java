package fa.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.training.entity.Content;
import fa.training.entity.Member;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    List<Content> findAllByMember(Member loggedInUser);

    Optional<Content> findByMemberAndId(Member loggedInUser, Integer contentId);

    @Query("SELECT c FROM Content c WHERE c.member = :member AND (:keyword IS NULL OR :keyword = '' OR c.title LIKE %:keyword% OR c.brief LIKE %:keyword% OR c.content LIKE %:keyword%)")
    Page<Content> findAllByMemberAndKeyword(@Param("keyword") String keyword, @Param("member") Member member,
	    Pageable pageable);
}
