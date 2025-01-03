package savenow.backend.dto.history;

import lombok.Getter;
import lombok.Setter;

public class HistoryReqDto {

    @Getter @Setter
    public static class MonthlyReq {
        private String year;
        private String month;
        private Long userId;
    }


}
