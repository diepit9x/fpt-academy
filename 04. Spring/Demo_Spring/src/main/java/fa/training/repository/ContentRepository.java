package fa.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Integer> {
	
}
