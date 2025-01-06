package savenow.backend.dto.daily;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class DailyResDto {

    @Getter @Setter
    public static class DailyData {
        private String date;
        private Long income;
        private Long expense;
        private String feedback;

        public DailyData(String date, Long income, Long expense, String feedback) {
            this.date = date;
            this.income = income;
            this.expense = expense;
            this.feedback = feedback;
        }

    }

    @Getter @Setter
    public static class MonthlyData {
        private Map<String, DailyData> days;

        public MonthlyData(Map<String, DailyData> days) {
            this.days = days;
        }
    }


}
