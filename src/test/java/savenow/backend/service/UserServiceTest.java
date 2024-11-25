package savenow.backend.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import savenow.backend.domain.Gender;
import savenow.backend.domain.User;
import savenow.backend.domain.UserRepository;
import savenow.backend.dto.user.UserReqDto.JoinReqDto;
import savenow.backend.dto.user.UserResDto.JoinResDto;
import savenow.backend.dummy.DummyObject;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Mock 환경 테스트 진행
class UserServiceTest extends DummyObject{

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_test() throws Exception{
        //given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("솔빈");
        joinReqDto.setEmail("aaaa@naver.com");
        joinReqDto.setPassword("1234");
        joinReqDto.setBirth(021204L);
        joinReqDto.setGender(Gender.MALE);

        //stub1
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        //stub2
        User newUser = newMockUser(1L, "솔빈", "aaaa@naver.com");
        when(userRepository.save(any())).thenReturn(newUser);

        //when
        JoinResDto joinResDto = userService.join(joinReqDto);
        System.out.println("테스트 : " + joinResDto);

        //then
        Assertions.assertThat(joinResDto.getId()).isEqualTo(1L);
        Assertions.assertThat(joinResDto.getName()).isEqualTo("솔빈");
    }

}