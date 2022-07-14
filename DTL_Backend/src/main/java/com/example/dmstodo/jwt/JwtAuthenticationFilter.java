package com.example.dmstodo.jwt;

import com.example.dmstodo.exception.TokenInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);//JWT를 받아온다.
        if(token!= null && jwtTokenProvider.validateToken(token)) { // 유효한 트콘인지 확인한다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token); //토큰이 유효하면 정보를 가져온다.
            SecurityContextHolder.getContext().setAuthentication(authentication); //securitycontextholder에 인증정보를 저장한다.
            logger.info("token is valid");
        }
        chain.doFilter(request, response);
    }
}
