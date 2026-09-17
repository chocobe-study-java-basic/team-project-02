package com.budzet.global.rq;

import com.budzet.domain.user.entity.User;
import com.budzet.domain.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Rq {
    private final UserService userService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

//    public User getActor(){
//        String headerAuthorization = getHeader("Authorization", "");
//
//        String accessToken = null;
//    }

    public void addCookie(String name, String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }

    public String getHeader(String name, String defaultValue){
        return Optional
                .ofNullable(request.getHeader(name))
                .filter(headerValue -> !headerValue.isBlank())
                .orElse(defaultValue);
    }
}
