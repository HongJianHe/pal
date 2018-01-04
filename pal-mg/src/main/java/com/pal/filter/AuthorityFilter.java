package com.pal.filter;

import com.google.common.base.Joiner;
import com.pal.util.DescryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

/**
 * Created by Hongjian_He on 2018/1/4.
 */
@Order(1)
@WebFilter(filterName = "AuthorityFilter", urlPatterns = "/*")
@Slf4j
public class AuthorityFilter implements Filter{



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("***************************执行中*********************");

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String token = httpRequest.getHeader("token");
        String accountId = httpRequest.getHeader("accountId");
        //验证信息
        /**
         *   1.header 里面必须存在 用户ID，token   两个字段   注： token：用户名+ ID+ 特定符号
         *   2.
         *
         */
        if(token == null || token == "" || accountId == null || accountId  == "" ){
            throw new WebApplicationException(HttpStatus.FORBIDDEN.value());
        }else{
            try {
                String checkAfter= Joiner.on("#").join(new String[]{accountId,token});
                String decrypt = DescryptUtil.decrypt(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }


    @Override
    public void destroy() {

    }
}
