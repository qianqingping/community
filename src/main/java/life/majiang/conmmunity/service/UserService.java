package life.majiang.conmmunity.service;

import life.majiang.conmmunity.mapper.UserMapper;
import life.majiang.conmmunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
      User dbUser = userMapper.findByAcountId(user.getAccountId());
      if (dbUser==null){

          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);

      }else {
          dbUser.setGmtModified(System.currentTimeMillis());
          dbUser.setAvatarUrl(user.getAvatarUrl());
          dbUser.setName(user.getName());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);
      }
    }
}
