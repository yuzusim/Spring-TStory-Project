package site.metacoding.blogv3.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.metacoding.blogv3._core.exception.Exception404;
import site.metacoding.blogv3.category.Category;
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

    // 게시글 쓰기
    @Transactional
    public void postSave(Integer sessionUserId, PostRequest.SaveDTO reqDTO){
        User sessionUser = userJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("회원 정보가 존재하지 않습니다."));

        Category category = categoryJPARepo.findById(reqDTO.getCategoryId())
                .orElseThrow(() -> new Exception404("카테고리가 존재하지 않습니다"));

//        // Quill 에디터에서 가져온 content를 reqDTO에서 가져온다고 가정
//        String content = reqDTO.getContent(); // reqDTO에 content 필드가 존재해야 함

        postJPARepo.save(reqDTO.toEntity(sessionUser, category, reqDTO.getContent(), reqDTO.getThumbnailFile()));

    }

    // 게시글 리스트
    public List<PostResponse.ListDTO> postList(Integer sessionUserId){
        // postJPARepo.findByPostList(sessionUserId) 메서드를 호출하여,
        // 데이터베이스에서 sessionUserId에 해당하는 사용자의 게시글 목록을 조회
        List<PostResponse.ListDTO> postLists = postJPARepo.findByPostList(sessionUserId);
        System.out.println("postLists"+ postLists);
        return postLists;
    }

    // 게시글 -> 카테고리 리스트
    @Transactional
    public PostResponse.WriteFormDTO writeform(Integer sessionUserId){
        User sessionUser = userJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));

        // categoryJPARepo에서 sessionUser.getId()로 유저의 카테고리 목록을 조회하여 categoryList에 담음
        List<CategoryResponse.CategoryNameDTO> categoryList = categoryJPARepo.findByUserId(sessionUser.getId());
        System.out.println("categoryList = " + categoryList);

//        // 카테고리 리스트가 비어있는 경우를 확인하여 로그 출력
//        if (categoryList.isEmpty()) {
//            System.out.println("No categories found for userId: " + sessionUser.getId());
//        }

        // categoryList를 사용하여 PostResponse.WriteFormDTO 객체를 생성하고, 이를 writeFormDTO 변수에 할당
        PostResponse.WriteFormDTO writeFormDTO = new PostResponse.WriteFormDTO(categoryList);

        return writeFormDTO;
    }


}
