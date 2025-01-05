package savenow.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import savenow.backend.dto.daily.DailyReqDto.GetDailyReq;
import savenow.backend.dto.daily.DailyResDto.*;
import savenow.backend.entity.daily.Daily;
import savenow.backend.entity.user.User;
import savenow.backend.entity.user.UserRepository;
import savenow.backend.handler.exception.CustomApiException;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final UserRepository userRepository;

    public MonthlyIncomeData getMonthIncome(GetDailyReq getDailyReq) {

        String year = getDailyReq.getYear();
        String month = getDailyReq.getMonth();
        User user = userRepository.findById(getDailyReq.getUserId()).orElseThrow(
                () ->  new CustomApiException("회원을 찾을 수 없습니다.")
        );

        List<Daily> dailyList = user.getDailyList();

        Map<String, DailyIncomeData> dailyDataMap = new HashMap<>();

        for (Daily daily : dailyList) {
            if (year.equals(String.valueOf(daily.getDate().getYear()))
                    && month.equals(String.valueOf(daily.getDate().getMonth()))
            ) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
                String date = daily.getDate().format(formatter);
                Long amount = daily.getIncome();
                DailyIncomeData dailyIncomeData = new DailyIncomeData(date, amount);

                dailyDataMap.put(date, dailyIncomeData);
            }
        }

        return new MonthlyIncomeData(dailyDataMap);
    }


    public MonthlyExpenseData getMonthExpense(GetDailyReq getDailyReq) {

        String year = getDailyReq.getYear();
        String month = getDailyReq.getMonth();
        User user = userRepository.findById(getDailyReq.getUserId()).orElseThrow(
                () ->  new CustomApiException("회원을 찾을 수 없습니다.")
        );

        List<Daily> dailyList = user.getDailyList();

        Map<String, DailyExpenseData> dailyDataMap = new HashMap<>();

        for (Daily daily : dailyList) {
            if (year.equals(String.valueOf(daily.getDate().getYear()))
                    && month.equals(String.valueOf(daily.getDate().getMonth()))) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
                String date = daily.getDate().format(formatter);
                Long amount = daily.getExpense();
                DailyExpenseData dailyExpensedata = new DailyExpenseData(date, amount);

                dailyDataMap.put(date, dailyExpensedata);
            }
        }

        return new MonthlyExpenseData(dailyDataMap);
    }


    public MonthlyData getMonthlydata(GetDailyReq getDailyReq) {

        String year = getDailyReq.getYear();
        String month = getDailyReq.getMonth();
        User user = userRepository.findById(getDailyReq.getUserId()).orElseThrow(
                () ->  new CustomApiException("회원을 찾을 수 없습니다.")
        );

        List<Daily> dailyList = user.getDailyList();

        Map<String, DailyData> dailyDataMap = new HashMap<>();

        for (Daily daily : dailyList) {
            if (year.equals(String.valueOf(daily.getDate().getYear()))
                    && month.equals(String.valueOf(daily.getDate().getMonth()))) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
                String date = daily.getDate().format(formatter);
                Long income = daily.getIncome();
                Long expense = daily.getExpense();
                DailyData dailyData = new DailyData(date, income, expense);

                dailyDataMap.put(date, dailyData);
            }
        }

        return new MonthlyData(dailyDataMap);
    }



}
