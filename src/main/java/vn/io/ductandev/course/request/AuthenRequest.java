package vn.io.ductandev.course.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenRequest(
        //regex
        @NotNull(message = "Email không được phép null")
        @NotEmpty(message = "Email không được phép rỗng")
        @Email(message = "Không phải định dạng email")
        String email,

        @NotNull(message = "Pass không được phép null")
        @NotEmpty(message = "Password không được phép rỗng")
        String password) {
}
