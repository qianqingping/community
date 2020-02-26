package life.majiang.conmmunity.controller;

import life.majiang.conmmunity.dto.AccessTokenDto;
import life.majiang.conmmunity.dto.GithubUser;
import life.majiang.conmmunity.provider.GithubPrivider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubPrivider githubPrivider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("Iv1.da649b87bf24b198");
        accessTokenDto.setClient_secret("c9819ef73674d3cba790238714f910fbfca331d4");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url("http://localhost:8887/callback");
        accessTokenDto.setState(state);
        String accessToken = githubPrivider.getAccessToken(accessTokenDto);
        GithubUser user = githubPrivider.getUer(accessToken);
        System.out.println(user.getName());
        return"index";

    }
}
