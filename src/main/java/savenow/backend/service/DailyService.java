package savenow.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savenow.backend.dto.daily.DailyReqDto.GetDailyReq;
import savenow.backend.dto.daily.DailyResDto.*;
import savenow.backend.entity.daily.Daily;
import savenow.backend.entity.daily.DailyRepository;
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
    private final DailyRepository dailyRepository;

    @Transactional(readOnly = true)
    public MonthlyData getMonthlydata(GetDailyReq getDailyReq) {

        // 유저 검색
        User user = userRepository.findById(getDailyReq.getUserId()).orElseThrow(
                () ->  new CustomApiException("회원을 찾을 수 없습니다.")
        );

        // 유저에 포함돼 있는 daillList 추출
        List<Daily> dailyList = dailyRepository.findByUser(user);

        // dailyData를 정렬할 hash 테이블
        Map<String, DailyData> dailyDataMap = new HashMap<>();

        for (Daily daily : dailyList) {
            if (checkDate(daily,getDailyReq)) {

                // 날짜 형식
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
                String date = daily.getDate().format(formatter);

                // 각 데이터의 응답 여부에 따른 null 값 혹은 데이터 생성
                Long income = getDailyReq.isIncludeIncome() ? daily.getIncome() : null;
                Long expense = getDailyReq.isIncludeExpense() ? daily.getExpense() : null;
                String feedback = getDailyReq.isIncludeFeedback() ? daily.getFeedback() : null;
                DailyData dailyData = new DailyData(date, income, expense,feedback);

                dailyDataMap.put(date, dailyData);
            }
        }

        String date = getDailyReq.getYear() + "/" + getDailyReq.getMonth();

        return new MonthlyData(date,dailyDataMap);
    }


    // 날짜가 같은지 확인하는 로직
    public boolean checkDate(Daily daily,GetDailyReq getDailyReq) {
        String year = getDailyReq.getYear();
        String month = getDailyReq.getMonth();
        return year.equals(String.valueOf(daily.getDate().getYear()))
                && month.equals(String.valueOf(daily.getDate().getMonthValue()));
    }

}
