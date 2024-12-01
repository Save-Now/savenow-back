package savenow.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import savenow.backend.dto.ResponseDto;
import savenow.backend.dto.user.UserResDto.JoinResDto;
import savenow.backend.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static savenow.backend.dto.user.UserReqDto.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController // 데이터 리턴 서버
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        // 유효성 검사
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
        }

        JoinResDto joinResDto = userService.join(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", joinResDto), HttpStatus.CREATED);
    }

    @PostMapping("/join/emailCheck")
    public ResponseEntity<?> emailCheck(@RequestBody @Valid EmailCheckDto emailCheckDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
        }
        int ed = userService.emailDuplicateCheck(emailCheckDto.getEmail());
        if(ed == 1) {
            return new ResponseEntity<>(new ResponseDto<>(-1,"이메일 중복 검사 실패",null),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto<>(1, "이메일 중복 검사 성공", null), HttpStatus.OK);
    }

    @PostMapping("/join/usernameCheck")
    public ResponseEntity<?> usernameCheck(@RequestBody @Valid NameCheckDto nameCheckDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ResponseDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
        }
        int nd = userService.nameDuplicateCheck(nameCheckDto.getUsername());
        if (nd == 1) {
            return new ResponseEntity<>(new ResponseDto<>(-1, "닉네임 중복 검사 실패", null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto<>(1, "닉네임 중복 검사 성공", null), HttpStatus.OK);
    }
}
