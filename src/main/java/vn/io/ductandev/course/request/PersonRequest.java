package vn.io.ductandev.course.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PersonRequest(
        //regex
        @NotNull(message = "Email không được phép null")
        @NotEmpty(message = "Email không được phép rỗng")
        @Email(message = "Không phải định dạng email")
        String username,

        @NotNull(message = "Pass không được phép null")
        @NotEmpty(message = "Password không được phép rỗng")
        String password,

        @NotNull(message = "FirstName không được phép null")
        @NotEmpty(message = "FirstName không được phép rỗng")
        String firstName,

        @NotNull(message = "LastName không được phép null")
        @NotEmpty(message = "LastName không được phép rỗng")
        String lastName) {
}
