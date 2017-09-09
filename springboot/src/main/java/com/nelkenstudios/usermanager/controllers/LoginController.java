package com.nelkenstudios.usermanager.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/web/login")
public class LoginController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

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
        m.addObject("user", principal);
        m.addObject("auth", authentication);
        m.addObject("req", request.getUserPrincipal());
        return m;
    }


}
