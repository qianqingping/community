package life.majiang.conmmunity.controller;

import life.majiang.conmmunity.mapper.QuestionMapper;
import life.majiang.conmmunity.model.Question;
import life.majiang.conmmunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;



    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model
    ){

        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("erro","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_modified());
        questionMapper.create(question);
        return "redirect:/";
    }
}
