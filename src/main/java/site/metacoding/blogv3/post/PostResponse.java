package site.metacoding.blogv3.post;

import lombok.Data;
import site.metacoding.blogv3.category.CategoryResponse;
import site.metacoding.blogv3.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponse {

    // 게시글 상세보기
    // 내부클래스로 만듦, 모든걸 디티오로 옮기는 방식
    @Data
    public static class DetailDTO {
        private Integer postId;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private String createdAt;
        private Boolean isPostOwner;

        public DetailDTO(Post post, User sessionUser){
            this.postId = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.userId = post.getUser().getId();
            this.username = post.getUser().getUsername();
            this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            // 세션유저 받아서 isPostOwner 만듬
            isPostOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == post.getUser().getId()) {
                    isPostOwner = true;
                }
            }
        }

    }

    // 게시글 리스트
    @Data
    public static class ListDTO {
        private Integer postId;
        private String thumbnailFile; //썸네일
        private String title;
        private String content;
        private String createdAt;


        public ListDTO(Integer postId, String thumbnailFile, String title, String content, LocalDateTime createdAt) {
            this.postId = postId;
            this.thumbnailFile = thumbnailFile;
            this.title = title;
            this.content = content;
            // 여기서 바로 포맷팅 적용 (분 까지)
            this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    @Data
    public static class PostDTO {
        //썸네일, 내용(flow건거), 제목, 생성날짜
        private Integer postId;
        private Integer userId; // = blogUserId and userId
        private String thumbnailFile;
        private String title;
        private String content;
        private String createdAt;
        private String username;

        public PostDTO(Post post) {
            this.postId = post.getId();
            this.userId = post.getUser().getId();
            this.thumbnailFile = post.getThumbnailFile();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)"));
            this.username = post.getUser().getUsername();
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
