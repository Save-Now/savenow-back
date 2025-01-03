package savenow.backend.entity.daily;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "daily_tb")
public class Daily {

    @Id @GeneratedValue
    private Long id;

}
