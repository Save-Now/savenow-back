package savenow.backend.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import savenow.backend.domain.User;

public class UserResDto {

    @Getter
    @Setter
    @ToString
    public static class JoinResDto{
        private Long id;
        private String name;

        public JoinResDto(User user) {
            this.id = user.getId();
            this.name = user.getUsername();
        }
    }
}
