package savenow.backend.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savenow.backend.domain.User;
import savenow.backend.domain.UserRepository;
import savenow.backend.dto.user.UserReqDto.JoinReqDto;
import savenow.backend.dto.user.UserResDto.JoinResDto;
import savenow.backend.handler.exception.CustomApiException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BCryptPasswordEncoder passwordEncoder;


    // 회원 가입 서비스
    @Transactional
    public JoinResDto join(JoinReqDto joinReqDto) {
        // 1. 아이디 중복 검사
        Optional<User> userOP1 = userRepository.findByEmail(joinReqDto.getEmail());
        if (userOP1.isPresent()) {
            //이메일 중복
            throw new CustomApiException("이미 존재하는 이메일 입니다.");
        }

        // 2. 닉네임 중복 검사
        Optional<User> userOP2 = userRepository.findByUsername(joinReqDto.getUsername());
        if (userOP2.isPresent()) {
            // 닉네임 중복
            throw new CustomApiException("동일한 사용자 이름이 존재합니다.");
        }

        // 패스워드 인코딩 + 회원가입
        User newUser = userRepository.save(joinReqDto.toEntity(passwordEncoder));

        // 3. DTO 응답
        return new JoinResDto(newUser);
    }

}
