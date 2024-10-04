package site.metacoding.blogv3.post;

import lombok.Data;
import site.metacoding.blogv3.category.CategoryResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostResponse {

    @Data
    public static class Post{

    }

    @Data
    public static class WriteFormDTO {
        private List<CategoryResponse.CategoryNameDTO> categoryNameDTO;

        public WriteFormDTO(List<CategoryResponse.CategoryNameDTO> categoryNameDTO) {
            this.categoryNameDTO = categoryNameDTO;
        }


    }


}
