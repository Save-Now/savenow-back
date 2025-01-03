package savenow.backend.dto.history;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class HistoryResDto {

    @Getter @Setter
    public static class DailyRes {

        private String 날짜;
        private Long 수입;
        private Long 지출;

        public DailyRes(String 날짜, Long 수입, Long 지출) {
            this.날짜 = 날짜;
            this.수입 = 수입;
            this.지출 = 지출;
        }
    }

    @Getter @Setter
    public static class MonthlyRes {
        private Map<String, DailyRes> days;

        public MonthlyRes(Map<String, DailyRes> days) {
            this.days = days;
        }
    }



}
