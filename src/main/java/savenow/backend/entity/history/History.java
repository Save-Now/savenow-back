package savenow.backend.entity.history;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import savenow.backend.entity.daily.Daily;
import savenow.backend.entity.user.User;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "history_tb")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long amount;

    @Column
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HistoryEnum type; // 수입(INCOME) 또는 지출(EXPENSE)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAILY_ID")
    private Daily daily;

    @Builder
    public History(Long id, Long amount, LocalDate date, User user, Daily daily,HistoryEnum type) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.user = user;
        this.daily = daily;
    }
}