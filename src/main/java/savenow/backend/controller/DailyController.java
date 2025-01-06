package savenow.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.dto.daily.DailyReqDto.GetDailyReq;
import savenow.backend.dto.daily.DailyResDto.MonthlyData;
import savenow.backend.service.DailyService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DailyController {

    private final DailyService dailyService;

    // 불필요한 데이터에 false로 끼워넣는 방식
    //  /api/getMonthlyData?year=2024&month=11&userId=1 >> 수입, 지출, 피드백 모두 응답
    // /api/getMonthlyData?year=2024&month=11&userId=1&includeIncome=false >> 지출, 피드백만 응답
    @GetMapping("/getMonthlyData")
    public ResponseEntity<?> getMonthlyData (@ModelAttribute GetDailyReq getDailyReq) {
        MonthlyData monthlyData = dailyService.getMonthlydata(getDailyReq);
        return ResponseEntity.ok(monthlyData);
    }

}
