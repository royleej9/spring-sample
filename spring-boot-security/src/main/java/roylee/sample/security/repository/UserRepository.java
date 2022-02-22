package roylee.sample.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roylee.sample.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
       Optional<User> findOneByUserId(String userId);
}
