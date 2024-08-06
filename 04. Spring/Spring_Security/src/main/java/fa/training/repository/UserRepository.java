package fa.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

}
