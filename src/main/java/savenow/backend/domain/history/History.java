package savenow.backend.domain.history;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import savenow.backend.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "history_tb")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Long amount;

    @Column
    private String memo;

    @Column
    private LocalDateTime date;

    @CreatedDate //Insert
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate //Update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public History(Long id, Long amount, String memo, LocalDateTime date, LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.id = id;
        this.amount = amount;
        this.memo = memo;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}
