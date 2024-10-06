package site.metacoding.blogv3.post;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import site.metacoding.blogv3._core.util.ImageUtil;
import site.metacoding.blogv3.category.Category;
import site.metacoding.blogv3.user.User;

public class PostRequest {

    // 게시글 쓰기
    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private Integer categoryId;
        private MultipartFile thumbnailFile;
    }

}
