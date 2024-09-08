package site.metacoding.blogv3.category;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final HttpSession session;

    @GetMapping("/category/write-form")
    public String writeForm() {

        return "category/writeForm";
    }

}
