package savenow.backend.domain.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import savenow.backend.domain.account.Account;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Category(Long id, String name, Account account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }
}
