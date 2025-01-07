package savenow.backend.dto.history;

import lombok.Data;
import savenow.backend.entity.history.HistoryEnum;

import java.time.LocalDate;

@Data
public class HistoryReqDto {
    private Long userId;          // 로그인된 사용자 ID
    private Long amount;          // 금액
    private LocalDate date;       // 날짜
    private HistoryEnum type;     // INCOME/EXPENSE
}