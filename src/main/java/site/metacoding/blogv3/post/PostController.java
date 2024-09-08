package site.metacoding.blogv3.post;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final HttpSession session;

    @GetMapping("/")
    public String main() {

        return "main";
    }

    @GetMapping("/post/detail")
    public String postDetail() {

        return "post/detail";
    }

    @GetMapping("/post/list")
    public String postList() {

        return "post/list";
    }

    @GetMapping("/post/write-form")
    public String postWriteForm() {

        return "post/writeForm";
    }

}
