package com.angel.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * user: zhoufangchao
 * date: 2018/9/17
 */
@WebServlet(urlPatterns = "/DateLocal")
public class DateLocale extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        // 获取客户端的区域设置
        Locale locale = req.getLocale( );
        String date = DateFormat.getDateTimeInstance(
                DateFormat.FULL,
                DateFormat.SHORT,
                locale).format(new Date( ));

        String title = "特定于区域设置的日期";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + date + "</h1>\n" +
                "</body></html>");
    }
}
