package com.example.demo.presetation;

import com.example.demo.application.security.auth.LoginUser;
import com.example.demo.application.dto.UserDto;
import com.example.demo.application.UserService;
import com.example.demo.application.validator.CustomValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private final CustomValidators.EmailValidator EmailValidator;
    private final CustomValidators.NicknameValidator NicknameValidator;;
    private final CustomValidators.UsernameValidator UsernameValidator;;

    @InitBinder
    public void valiatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValidator, NicknameValidator, UsernameValidator);
    }

    @GetMapping("/auth/join")
    public String join(){ return "/user/user-join"; }

}
