package savenow.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.dto.daily.DailyReqDto.GetDailyReq;
import savenow.backend.dto.daily.DailyResDto.MonthlyData;
import savenow.backend.dto.daily.DailyResDto.MonthlyExpenseData;
import savenow.backend.dto.daily.DailyResDto.MonthlyIncomeData;
import savenow.backend.service.DailyService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping("/getIncome")
    public ResponseEntity<?> getMonthlyIncome(@ModelAttribute GetDailyReq getDailyReq) {
        MonthlyIncomeData monthlyIncomeData = dailyService.getMonthIncome(getDailyReq);
        return ResponseEntity.ok(monthlyIncomeData);
    }

    @GetMapping("/getExpense")
    public ResponseEntity<?> getMonthlyExpense (@ModelAttribute GetDailyReq getDailyReq) {
        MonthlyExpenseData monthlyExpenseData = dailyService.getMonthExpense(getDailyReq);
        return ResponseEntity.ok(monthlyExpenseData);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getMonthlyData (@ModelAttribute GetDailyReq getDailyReq) {
        MonthlyData monthlyData = dailyService.getMonthlydata(getDailyReq);
        return ResponseEntity.ok(monthlyData);
    }

}
