package savenow.backend.entity.daily;

import org.springframework.data.jpa.repository.JpaRepository;
import savenow.backend.entity.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    Optional<Daily> findByUserAndDate(User user, LocalDate date);

    List<Daily> findByUser(User user);
}
