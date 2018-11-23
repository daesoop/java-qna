package codesquad.question;

import codesquad.user.HttpSessionUtils;
import codesquad.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
public class QuestionController {

    @Autowired
    private QnaRepository qnaRepository;

    @GetMapping("/form")
    public String form(HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "user/login";
        }
        return "qna/form";
    }

    @PostMapping("/question")
    public String question(HttpSession session , String contents, String title) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "user/login";
        }
        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        Question question = new Question(sessionUser.getUserId(), title, contents);
        qnaRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String qnaShow(Model model, @PathVariable Long id) {
        model.addAttribute("question", qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("질문이 없습니다.")));
        return "qna/show";
    }
}