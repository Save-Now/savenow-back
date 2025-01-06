package savenow.backend.entity.daily;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import savenow.backend.entity.history.History;
import savenow.backend.entity.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Long income = 0L;

    @Column
    private Long expense = 0L;

    @Column(nullable = false)
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "daily", fetch = FetchType.LAZY)
    private List<History> histories = new ArrayList<>();

    @Builder
    public Daily(Long id, LocalDate date, Long income, Long expense, String feedback, User user) {
        this.id = id;
        this.date = date;
        this.income = income;
        this.expense = expense;
        this.feedback = feedback;
        this.user = user;
    }

    public void update(Long income, Long expense, String feedback) {
        this.income = income;
        this.expense = expense;
        this.feedback = feedback;
    }

    public void addHistory(History history) {
        histories.add(history);
        history.setDaily(this);
    }
}
