package br.com.usp.mac0332.snippettool.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;

public class CheckAuthCookieFilter implements javax.servlet.Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws javax.servlet.ServletException {

    }

    @Override
    public void doFilter(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, javax.servlet.FilterChain filterChain) throws IOException, javax.servlet.ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest((javax.servlet.http.HttpServletRequest) httpServletRequest);

        jakarta.servlet.http.Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                logger.debug(cookie.getName() + " : " + cookie.getValue());
                if (cookie.getName().equalsIgnoreCase("auth")) {
                    mutableRequest.putHeader("Authorization", URLDecoder.decode(cookie.getValue(), "utf-8"));
                }
            }
        }

        filterChain.doFilter(mutableRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
