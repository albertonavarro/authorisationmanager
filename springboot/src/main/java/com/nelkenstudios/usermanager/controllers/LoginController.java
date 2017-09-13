package com.nelkenstudios.usermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Controller
@RequestMapping("/web/login")
public class LoginController {

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @GetMapping("/a")
    public ModelAndView login(Principal principal, Authentication authentication, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("index");
        m.addObject("user", principal);
        m.addObject("auth", authentication);
        m.addObject("req", request.getUserPrincipal());
        return m;
    }

    @GetMapping("/b")
    public ModelAndView token(Principal principal, Authentication authentication, HttpServletRequest request) {
        ModelAndView m = new ModelAndView("token");
        DefaultTokenServices dts = new DefaultTokenServices();
        dts.setTokenStore(new JwtTokenStore(accessTokenConverter));

        OAuth2AccessToken token = accessTokenConverter
                .enhance(
                        tokenEnhancer()
                                .enhance(dts.createAccessToken((OAuth2Authentication) authentication), (OAuth2Authentication) authentication),
                        (OAuth2Authentication) authentication);

        m.addObject("jwttoken", token.toString());
        return m;
    }

    public class CustomTokenEnhancer implements TokenEnhancer {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("organization", authentication.getName() + randomAlphabetic(4));
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        }
    }


    @Bean("w1")
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


}
