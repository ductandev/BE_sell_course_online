package vn.io.ductandev.course.request;


public record UserRequest(
        String username,
        String email,
        String password,
        String avatar
//        int role_id;
)
{}

