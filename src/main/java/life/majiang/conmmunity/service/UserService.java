package life.majiang.conmmunity.service;

import life.majiang.conmmunity.mapper.UserMapper;
import life.majiang.conmmunity.model.User;
import life.majiang.conmmunity.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
      if (users.size()==0){

          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);

      }else {
          User dbUser = users.get(0);
          User updateUser = new User();
          updateUser.setGmtModified(System.currentTimeMillis());
          updateUser.setAvatarUrl(user.getAvatarUrl());
          updateUser.setName(user.getName());
          updateUser.setToken(user.getToken());
          UserExample example = new UserExample();
           example.createCriteria()
                   .andIdEqualTo(dbUser.getId());
          userMapper.updateByExampleSelective(updateUser,example);
      }
    }
}
