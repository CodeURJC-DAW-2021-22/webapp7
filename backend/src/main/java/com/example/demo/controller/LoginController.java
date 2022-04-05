package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.security.jwt.LoginRequest;
import com.example.demo.security.jwt.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserLoginService userServiceLogin;

    @Autowired
    private RepositoryUserDetailsService userService;

    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return("login");
    }

    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "loginerror";
    }

    @GetMapping("/processLogOut")
    public void LogOut(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userServiceLogin.logout(request, response);

        model.addAttribute("loggedUser",false);
        model.addAttribute("logged",false);
        model.addAttribute("admin", false);
        response.sendRedirect("/");
    }

    @PostMapping("/processFormLogIn")
    public ModelAndView processForm(Model model, HttpServletRequest request,
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            @RequestBody LoginRequest loginRequest){

        userServiceLogin.login(loginRequest, accessToken, refreshToken);

        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());

        if (currentUserOptional.isPresent()) {
            return new ModelAndView(new RedirectView("/", true));
        }
        else
            return new ModelAndView(new RedirectView("/LogInError", true));
    }

}
