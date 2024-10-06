package site.metacoding.blogv3.post;

import lombok.Data;
import site.metacoding.blogv3.category.CategoryResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponse {

    @Data
    public static class Post {

    }

    // 게시글 리스트
    @Data
    public static class ListDTO {
        private Integer id;
        private String thumbnailFile; //섬네일
        private String title;
        private String content;
        private LocalDateTime createdAt;

        public ListDTO(Integer id, String thumbnailFile, String title, String content, LocalDateTime createdAt) {
            this.id = id;
            this.thumbnailFile = thumbnailFile;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
        }
    }


    // 게시글 쓰기 폼
    @Data
    public static class WriteFormDTO {
        // 카테고리 네임
        private List<CategoryResponse.CategoryNameDTO> categoryNameDTO;

        public WriteFormDTO(List<CategoryResponse.CategoryNameDTO> categoryNameDTO) {
            this.categoryNameDTO = categoryNameDTO;
        }

    }


}
