package savenow.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.entity.history.History;
import savenow.backend.entity.history.HistoryRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private HistoryRepository historyRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addFeedback(@RequestBody History history) {
        historyRepository.save(history);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "피드백이 성공적으로 추가되었습니다.",
                "data", history
        ));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateFeedback(@RequestBody History history) {
        Optional<History> existing = historyRepository.findById(history.getDate());
        if (existing.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "failure",
                    "message", "해당 날짜의 피드백을 찾을 수 없습니다."
            ));
        }
        historyRepository.save(history);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "피드백이 성공적으로 수정되었습니다.",
                "data", history
        ));
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteFeedback(@RequestBody Map<String, String> request) {
        String date = request.get("date");
        if (!historyRepository.existsById(date)) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "failure",
                    "message", "해당 날짜의 피드백을 찾을 수 없습니다."
            ));
        }
        historyRepository.deleteById(date);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "피드백이 성공적으로 삭제되었습니다."
        ));
    }
}

