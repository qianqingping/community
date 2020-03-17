package life.majiang.conmmunity.service;

import life.majiang.conmmunity.dto.PaginationDto;
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

    public PaginationDto list(Integer page, Integer size) {

        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = questionMapper.count();
        paginationDto.setPageination(totalCount,page,size);
        if (page<1){
            page = 1;
        } if (page>paginationDto.getTotalPage()){
            page = paginationDto.getTotalPage();
        }

        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtos = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDto.setQuestions(questionDtos);

        return paginationDto;
    }

    public PaginationDto listByUserId(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = questionMapper.countByUserId(userId);
        paginationDto.setPageination(totalCount,page,size);
        if (page<1){
            page = 1;
        } if (page>paginationDto.getTotalPage()){
            page = paginationDto.getTotalPage();
        }

        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtos = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDto.setQuestions(questionDtos);

        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }
}
