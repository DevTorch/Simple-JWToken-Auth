package jwtokenauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Authentication request")
public class SignInRequest {

    @Schema(description = "Username", example = "John")
    @Size(min = 5, max = 50, message = "Username should be between 5 and 50 characters")
    @NotBlank(message = "This field should not be empty")
    private String username;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Password length should be between 8 and 255 characters")
    @NotBlank(message = "This field should not be empty")
    private String password;
}
