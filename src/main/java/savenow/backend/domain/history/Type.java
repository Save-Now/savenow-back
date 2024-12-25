package savenow.backend.domain.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {

    INCOME("수입"), OUTCOME("지출");
    private String value;
}
