package site.metacoding.blogv3.category;

import lombok.Data;

public class CategoryResponse {

    @Data
    public static class CategoryNameDTO{
        private Integer id;
        private String categoryName;

        // 기본 생성자 추가
        public CategoryNameDTO() {
        }

        public CategoryNameDTO(Integer id, String categoryName) {
            this.id = id;
            this.categoryName = categoryName;
        }
    }


}
