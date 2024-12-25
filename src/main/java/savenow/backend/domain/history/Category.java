package savenow.backend.domain.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    식비("식비"),여가("여가"),쇼핑("쇼핑"),경조사("경조사"),기타("기타");

    private String value;
}
