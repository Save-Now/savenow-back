package savenow.backend.entity.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, String> {
    // String은 엔티티의 ID 타입(date)
}
