package org.joker.oscp.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.util.CurrentUser;
import org.joker.oscp.gateway.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Jwt过滤器
 *
 * @author JOKER
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationTokenFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {

        this.jwtTokenUtil = jwtTokenUtil;

        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取来自前端的token
        String authHeader = httpServletRequest.getHeader(jwtTokenUtil.getHeader());
        if (authHeader != null) {
            // 根据该token获取用户名
            String username = jwtTokenUtil.getUsernameFromToken(authHeader);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 若token过期，则重新分配token
                if (jwtTokenUtil.validateToken(authHeader, userDetails)) {
                    HttpSession httpSession = httpServletRequest.getSession();
                    if (httpSession.getAttribute(authHeader) != null) {
//                        if (CurrentUser.getCurrentUser() == null) {
                        Long userId = (Long) httpSession.getAttribute(authHeader);
                        CurrentUser.saveUserId(userId);
//                        }
                    } else {
                        log.error("登录失败！");
                    }
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
