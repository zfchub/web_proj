package pers.husen.web.controller.releasefea;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.service.ReleaseFeatureSvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * user: zhoufangchao
 * date: 2018/9/27
 */
@Controller
@RequestMapping("/latestRlseFetr.hms")
public class LatestRlseFetrController {

    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String releaseId) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        ReleaseFeatureSvc rSvc = new ReleaseFeatureSvc();
        ReleaseFeatureVo rVo;

        /** 如果是请求最新版本 id为 null或者0 */
        if(releaseId == null || Integer.parseInt(releaseId) == 0) {
            rVo = rSvc.queryLatestReleaseFeature();
            String json = JSONObject.fromObject(rVo).toString();
            out.println(json);

            return;
        }
        /** 如果是请求其他版本 */
        if(releaseId != null && Integer.parseInt(releaseId) != 0) {
            rVo = rSvc.queryReleaseById(Integer.parseInt(releaseId));
            String json = JSONObject.fromObject(rVo).toString();
            out.println(json);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String releaseId) throws IOException {
        doGet(request, response, releaseId);
    }
}
