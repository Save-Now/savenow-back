package savenow.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savenow.backend.entity.User;

import java.util.Optional;

@Repository


/*
 * UserRepository
 * save 등 JpaRepository로 구현된 기능은 따로 테스트 진행 X
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); //JPA NamedQuery로 동작

    Optional<User> findByEmail(String email);
}