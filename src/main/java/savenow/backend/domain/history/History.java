package savenow.backend.domain.history;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import savenow.backend.domain.account.Account;

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
    @Column // 수입. 지출을 구분
    private Type type;
    @Column // 지출 유형을 구분
    private Category category;
    @Column
    private LocalDateTime date;

    @LastModifiedDate //Update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Builder
    public History(Long id, Long amount, String memo, Type type, Category category, LocalDateTime date, LocalDateTime updatedAt, Account account) {
        this.id = id;
        this.amount = amount;
        this.memo = memo;
        this.type = type;
        this.category = category;
        this.date = date;
        this.updatedAt = updatedAt;
        this.account = account;
    }
}
