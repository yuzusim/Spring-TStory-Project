package site.metacoding.blogv3.category;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site.metacoding.blogv3.user.User;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final HttpSession session;


    @PostMapping("/s/category/save")
    public String save(String categoryName) {
        System.out.println("categoryName = " + categoryName);
        User user = (User) session.getAttribute("sessionUser");
        categoryService.save(categoryName, user.getId());

        return "redirect:/s/category/write-form";

    }

    @GetMapping("/s/category/write-form")
    public String writeForm() {

        return "category/writeForm";
    }

}

