package com.angel.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * user: zhoufangchao
 * date: 2018/9/17
 */
@WebServlet(urlPatterns = "/PageHitCounter")
public class PageHitCounter extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private int hitCount;

    public void init()
    {
        // 重置点击计数器
        hitCount = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        // 增加 hitCount
        hitCount++;
        PrintWriter out = resp.getWriter();
        String title = "总点击量";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">" + hitCount + "</h2>\n" +
                "</body></html>");
    }
}
