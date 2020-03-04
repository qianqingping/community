package life.majiang.conmmunity.service;

import life.majiang.conmmunity.dto.QuestionDto;
import life.majiang.conmmunity.mapper.QuestionMapper;
import life.majiang.conmmunity.mapper.UserMapper;
import life.majiang.conmmunity.model.Question;
import life.majiang.conmmunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> list() {
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions = questionMapper.list();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }
}
