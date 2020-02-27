package life.majiang.conmmunity.controller;

import life.majiang.conmmunity.dto.AccessTokenDto;
import life.majiang.conmmunity.dto.GithubUser;
import life.majiang.conmmunity.mapper.UserMapper;
import life.majiang.conmmunity.model.User;
import life.majiang.conmmunity.provider.GithubPrivider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubPrivider githubPrivider;

    @Value("${github.client_id}")
    private String clientId;

    @Value("${github.client_secret}")
    private String secret;

    @Value("${github.redirect_url}")
    private String redirectUrl;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse reponse){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(redirectUrl);
        accessTokenDto.setState(state);
        String accessToken = githubPrivider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubPrivider.getUer(accessToken);
        if(githubUser!=null){
            //登录成功，xiecookie和session
            User user =  new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
           // request.getSession().setAttribute("user",githubUser);
            reponse.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }


    }
}
