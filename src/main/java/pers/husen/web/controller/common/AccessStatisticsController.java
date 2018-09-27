package pers.husen.web.controller.common;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.husen.web.bean.po.AccessAtatisticsPo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * user: zhoufangchao
 * date: 2018/9/27
 */
@Controller
@RequestMapping("/accessStatistics.hms")
public class AccessStatisticsController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        AccessAtatisticsPo aPo = new AccessAtatisticsPo();
        aPo.setAccessToday((Integer) request.getServletContext().getAttribute("visitToday"));
        aPo.setAccessTotal((Integer) request.getServletContext().getAttribute("visitTotal"));
        aPo.setOnlineCurrent((Integer) request.getServletContext().getAttribute("onlineCount"));

        String json = JSONObject.fromObject(aPo).toString();
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
