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
@WebServlet(urlPatterns = "/DisplaySpanish")
public class DisplaySpanish extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        // 设置西班牙语言代码
        resp.setHeader("Content-Language", "es");

        String title = "En Espa&ntilde;ol";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1>" + "En Espa&ntilde;ol:" + "</h1>\n" +
                "<h1>" + "&iexcl;Hola Mundo!" + "</h1>\n" +
                "</body></html>");
    }
}
