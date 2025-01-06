package savenow.backend.entity.history;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import savenow.backend.entity.daily.Daily;
import savenow.backend.entity.user.User;

import java.time.LocalDateTime;

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
    private LocalDateTime date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAILY_ID")
    private Daily daily;

    @Builder
    public History(Long id, Long amount, LocalDateTime date, User user, Daily daily) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.user = user;
        this.daily = daily;
    }
}