package savenow.backend.entity.daily;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import savenow.backend.entity.user.User;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "daily_tb")
public class Daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private Long income;

    @Column
    private Long expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Daily(Long id, LocalDate date, Long income, Long expense, User user) {
        this.id = id;
        this.date = date;
        this.income = income;
        this.expense = expense;
        this.user = user;
    }
}
