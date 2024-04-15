package jwtokenauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sign Up request") //OpenAPI Annotation
public class SignUpRequest {

    @Schema(description = "Username", example = "John")
    @Size(min = 5, max = 50, message = "Username should be between 5 and 50 characters")
    @NotBlank(message = "This field should not be blank")
    private String username;

    @Schema(description = "E-amil", example = "johndoe@gmail.com")
    @Size(min = 5, max = 255, message = "This field should be between 5 and 255 characters")
    @NotBlank(message = "This field should not be blank")
    @Email(message = "Email should be valid as example: johndoe@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(max = 255, message = "Password should be less than 255 characters")
    private String password;
}
