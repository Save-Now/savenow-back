package savenow.backend.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.dto.history.HistoryReqDto;
import savenow.backend.service.HistoryService;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    // 가계부 추가
    @PostMapping
    public ResponseEntity<String> addHistory(@RequestBody HistoryReqDto requestDto) {
        historyService.addHistory(requestDto);
        return new ResponseEntity<>("History added successfully.", HttpStatus.OK);
    }

    // 가계부 수정
    @PutMapping("/{historyId}")
    public ResponseEntity<String> updateHistory(@PathVariable Long historyId, @RequestBody HistoryReqDto requestDto) {
        historyService.updateHistory(historyId, requestDto);
        return new ResponseEntity<>("History updated successfully.", HttpStatus.OK);
    }

    // 가계부 삭제
    @DeleteMapping("/{historyId}")
    public ResponseEntity<String> deleteHistory(@PathVariable Long historyId) {
        historyService.deleteHistory(historyId);
        return new ResponseEntity<>("History deleted successfully.", HttpStatus.OK);
    }
}