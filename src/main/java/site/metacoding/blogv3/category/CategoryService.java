package site.metacoding.blogv3.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.metacoding.blogv3._core.exception.ApiException400;
import site.metacoding.blogv3._core.exception.ApiException404;
import site.metacoding.blogv3.user.User;
import site.metacoding.blogv3.user.UserJPARepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final UserJPARepository userJPARepo;
    private final CategoryJPARepository categoryJPARepo;

    @Transactional
    public void save(String categoryName, Integer sessionUserId) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new RuntimeException("카테고리 이름을 입력해주세요.");
        }

        User sessionUser = userJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

        Optional<Category> categoryOP = categoryJPARepo.findByCategoryNameAndUserId(categoryName, sessionUserId);

        if (categoryOP.isPresent()) {
            throw new RuntimeException("이미 존재하는 카테고리입니다.");}

        categoryJPARepo.save(Category.builder()
                .categoryName(categoryName)
                .user(sessionUser)
                .build());

//        if (categoryOP.isPresent()) {
//                throw new RuntimeException("이미 존재하는 카테고리입니다.");
//        } else {
//            categoryJPARepo.save(Category.builder()
//                    .categoryName(categoryName)
//                    .user(sessionUser)
//                    .build());
//        }



    }


}
