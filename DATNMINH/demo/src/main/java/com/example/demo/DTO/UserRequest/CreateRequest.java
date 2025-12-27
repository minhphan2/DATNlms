package com.example.demo.DTO.UserRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.example.demo.model.User;
import jakarta.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest {


    @NotBlank(message = "Fullname is required")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "Password is required")
    private String password;


    @NotNull(message = "Role is required")
    private User.Role role;


    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    private String avatar;

    @NotNull(message = "Status is required")
    private User.Status status;

}
