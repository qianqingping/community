package life.majiang.conmmunity.controller;

import life.majiang.conmmunity.dto.QuestionDto;
import life.majiang.conmmunity.mapper.QuestionMapper;
import life.majiang.conmmunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){


        QuestionDto questionDto = questionService.getById(id);
        questionService.incVIEW(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
