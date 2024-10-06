package site.metacoding.blogv3.post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.metacoding.blogv3.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final HttpSession session;

    @GetMapping("/")
    public String main(HttpServletRequest request) {

        return "main";
    }

    @GetMapping("/post/detail")
    public String postDetail() {

        return "post/detail";
    }

    // 게시글 리스트
    @GetMapping("/post/list")
    public String postList(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");

        // 서비스 계층의 postService.postList() 메서드를 호출하여, 해당 사용자가 작성한 게시글 목록을 조회
        // 현재 로그인한 사용자의 ID로 게시글을 필터링 함
        List<PostResponse.ListDTO> listDTOs = postService.postList(user.getId());
        request.setAttribute("model", listDTOs);

        return "post/list";
    }

    // 게시글 쓰기
    @PostMapping("/s/post/save")
    public String save(@ModelAttribute PostRequest.SaveDTO reqDTO){
        System.out.println("Post SaveDTO"+ reqDTO);

        User user = (User) session.getAttribute("sessionUser");
        postService.postSave(user.getId(), reqDTO);

        return "redirect:/post/list";
    }

    //게시글 쓰기 폼
    @GetMapping("/s/post/write-form")
    public String postWriteForm(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        PostResponse.WriteFormDTO writeFormDTOList = postService.writeform(user.getId());

        request.setAttribute("model", writeFormDTOList);

        // 카테고리 네임 비어 있는지 확인
        if (writeFormDTOList.getCategoryNameDTO().isEmpty()) {
            request.setAttribute("noCategory", true);
        } else {
            request.setAttribute("noCategory", false);
        }

        return "post/writeForm";
    }


}
