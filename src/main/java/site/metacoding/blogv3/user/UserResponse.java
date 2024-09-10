package site.metacoding.blogv3.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class UpdateDTO{
        private String username;
        private String email;

        public UpdateDTO(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}
