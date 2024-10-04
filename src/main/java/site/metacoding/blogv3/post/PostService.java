package site.metacoding.blogv3.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.metacoding.blogv3.category.CategoryJPARepository;
import site.metacoding.blogv3.category.CategoryResponse;
import site.metacoding.blogv3.user.User;
import site.metacoding.blogv3.user.UserJPARepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJPARepository postJPARepo;
    private final UserJPARepository userJPARepo;
    private final CategoryJPARepository categoryJPARepo;

    public PostResponse.WriteFormDTO writeform(Integer sessionUserId){
        User sessionUser = userJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

//        private List<CategoryResponse.CategoryNameDTO> categoryNameDTO;
//
//        public WriteFormDTO(List<CategoryResponse.CategoryNameDTO> categoryNameDTO) {
//            this.categoryNameDTO = categoryNameDTO;
//        }
        List<CategoryResponse.CategoryNameDTO> categoryList = categoryJPARepo.findByUserId(sessionUser.getId());
        System.out.println("categoryList = " + categoryList);

        PostResponse.WriteFormDTO writeFormDTO = new PostResponse.WriteFormDTO(categoryList);

        return writeFormDTO;
    }


}
