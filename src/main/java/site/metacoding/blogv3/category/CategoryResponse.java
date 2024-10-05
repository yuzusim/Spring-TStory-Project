package site.metacoding.blogv3.category;

import lombok.Data;

public class CategoryResponse {

    // 카테고리 네임
    @Data
    public static class CategoryNameDTO{
        private Integer id;
        private String categoryName;

        public CategoryNameDTO(Integer id, String categoryName) {
            this.id = id;
            this.categoryName = categoryName;
        }
    }
}
