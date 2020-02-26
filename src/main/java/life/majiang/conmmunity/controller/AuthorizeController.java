package life.majiang.conmmunity.controller;

import life.majiang.conmmunity.dto.AccessTokenDto;
import life.majiang.conmmunity.dto.GithubUser;
import life.majiang.conmmunity.provider.GithubPrivider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(redirectUrl);
        accessTokenDto.setState(state);
        String accessToken = githubPrivider.getAccessToken(accessTokenDto);
        GithubUser user = githubPrivider.getUer(accessToken);
        if(user!=null){
            //登录成功，xiecookie和session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }


    }
}
