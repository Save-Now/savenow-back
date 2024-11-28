package savenow.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String newPassword;
    private String newUsername;
}
