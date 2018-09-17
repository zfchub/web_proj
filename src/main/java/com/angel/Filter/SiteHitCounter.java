package com.angel.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * user: zhoufangchao
 * date: 2018/9/17
 */
@WebFilter(urlPatterns = "/*")
public class SiteHitCounter implements Filter {

    private int hitCount;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 重置点击计数器
        hitCount = 0;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 把计数器的值增加 1
        hitCount++;

        // 输出计数器
        System.out.println("网站访问统计："+ hitCount );

        // 把请求传回到过滤器链
        chain.doFilter(request,response);
    }
}
