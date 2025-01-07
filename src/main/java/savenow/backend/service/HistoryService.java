package savenow.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savenow.backend.dto.history.HistoryReqDto;
import savenow.backend.entity.daily.Daily;
import savenow.backend.entity.history.History;
import savenow.backend.entity.history.HistoryEnum;
import savenow.backend.entity.user.User;
import savenow.backend.entity.daily.DailyRepository;
import savenow.backend.entity.history.HistoryRepository;
import savenow.backend.entity.user.UserRepository;


@Service
@RequiredArgsConstructor
public class HistoryService {

    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final DailyRepository dailyRepository;

    // 가계부 추가
    @Transactional
    public void addHistory(HistoryReqDto requestDto) {
        User user = findUser(requestDto.getUserId());

        // Daily 조회 또는 생성
        Daily daily = dailyRepository.findByUserAndDate(user, requestDto.getDate())
                .orElseGet(() -> {
                    Daily newDaily = Daily.builder()
                            .date(requestDto.getDate())
                            .user(user)
                            .build();
                    dailyRepository.save(newDaily);
                    return newDaily;
                });

        // History 생성 및 Daily 업데이트
        History history = History.builder()
                .amount(requestDto.getAmount())
                .date(requestDto.getDate())
                .type(requestDto.getType())
                .user(user)
                .daily(daily)
                .build();
        historyRepository.save(history);

        // Daily의 income/expense 업데이트
        if (requestDto.getType() == HistoryEnum.INCOME) {
            daily.setIncome(daily.getIncome() + requestDto.getAmount());
        } else {
            daily.setExpense(daily.getExpense() + requestDto.getAmount());
        }
    }

    // 가계부 수정
    @Transactional
    public void updateHistory(Long historyId, HistoryReqDto requestDto) {
        History history = findHistory(historyId);

        Daily daily = history.getDaily();

        // 기존 금액 반영 제거
        if (history.getType() == HistoryEnum.INCOME) {
            daily.setIncome(daily.getIncome() - history.getAmount());
        } else {
            daily.setExpense(daily.getExpense() - history.getAmount());
        }

        // History 업데이트
        history.setAmount(requestDto.getAmount());
        history.setType(requestDto.getType());

        // 새로운 금액 반영
        if (requestDto.getType() == HistoryEnum.INCOME) {
            daily.setIncome(daily.getIncome() + requestDto.getAmount());
        } else {
            daily.setExpense(daily.getExpense() + requestDto.getAmount());
        }
    }

    // 가계부 삭제
    @Transactional
    public void deleteHistory(Long historyId) {
        History history = findHistory(historyId);

        Daily daily = history.getDaily();

        // Daily 금액 업데이트
        if (history.getType() == HistoryEnum.INCOME) {
            daily.setIncome(daily.getIncome() - history.getAmount());
        } else {
            daily.setExpense(daily.getExpense() - history.getAmount());
        }

        // History 삭제
        historyRepository.delete(history);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    private History findHistory(Long historyId) {
        return historyRepository.findById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("History not found."));
    }
}

