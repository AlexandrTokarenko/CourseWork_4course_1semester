package com.example.coursework.controller.auth;

import com.example.coursework.dto.AuthenticationRequest;
import com.example.coursework.dto.UserRegisterDTO;
import com.example.coursework.entity.User;
import com.example.coursework.exception.UserAlreadyExistException;
import com.example.coursework.repository.UserRepository;
import com.example.coursework.service.dataServices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login( Model model) {
        return "user/auth/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute UserRegisterDTO user, Model model) {

        model.addAttribute("user",user);

        return "user/auth/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") UserRegisterDTO user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/auth/register";
        }

        try{
            userService.save(User.fromUserDTO(user));
        } catch (UserAlreadyExistException uaeEx) {
            return "user/auth/register";
        }

        return "user/auth/login";
    }

   /* @Deprecated
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@ModelAttribute AuthenticationRequest request) {
        System.out.println("username = " + request.getEmail());
        System.out.println("password = " + request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return ResponseEntity.ok("Ok");
    }*/
}
