package com.example.demo.controller.restController;

import com.example.demo.security.jwt.AuthResponse;
import com.example.demo.security.jwt.LoginRequest;
import com.example.demo.security.jwt.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/LogIn")
    public ResponseEntity<AuthResponse> processForm(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            @RequestBody LoginRequest loginRequest){
        return userLoginService.login(loginRequest, accessToken, refreshToken);

    }

    @PostMapping("/Refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {

        return userLoginService.refresh(refreshToken);
    }

    @GetMapping("/LogOut")
    public ResponseEntity<AuthResponse> LogOut(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, userLoginService.logout(request, response)));
    }
}
