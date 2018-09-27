package pers.husen.web.controller.userinfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.service.UserInfoSvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/users/query.hms")
public class UserQueryController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) Integer draw,
                      @RequestParam(required = false) Integer start,
                      @RequestParam(required = false) Integer length) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        UserInfoVo uVo = new UserInfoVo();
        UserInfoSvc uSvc = new UserInfoSvc();

        Integer recordsTotal = uSvc.queryUserTotalCount(uVo);
        ArrayList<UserInfoVo> uVos = uSvc.queryUserPerPage(uVo, length, start);
        String json = JSONArray.fromObject(uVos).toString();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("draw", draw);
        jsonObject.put("recordsTotal", recordsTotal);
        jsonObject.put("recordsFiltered", recordsTotal);
        jsonObject.element("data", json);

        out.println(jsonObject);
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) Integer draw,
                       @RequestParam(required = false) Integer start,
                       @RequestParam(required = false) Integer length) throws ServletException, IOException {
        doGet(request, response, draw, start, length);
    }
}
