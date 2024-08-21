package vn.io.ductandev.course.request;

public record UserRequestPatch(
        String username,
        String password,
        String avatar
) {

}
