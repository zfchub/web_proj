package pers.husen.web.controller.releasefea;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.ReleaseFeatureSvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/editRlseFetr.hms")
public class EditRlseFetrController {

    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String newArticle,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String releaseId) throws ServletException, IOException {
        JSONObject jsonObject = JSONObject.fromObject(newArticle);
        ReleaseFeatureVo rVo = TypeConvertHelper.jsonObj2ReleaseBean(jsonObject);

        ReleaseFeatureSvc rSvc = new ReleaseFeatureSvc();
        PrintWriter out = response.getWriter();
        int result = 0;

        String requestType = type;
        /** 如果是请求创建新版本 */
        if(RequestConstants.REQUEST_TYPE_CREATE.equals(requestType)) {
            result = rSvc.insertReleaseFeature(rVo);

            out.println(result);

            return;
        }
        /** 如果是请求编辑版本 */
        if(RequestConstants.REQUEST_TYPE_MODIFY.equals(requestType)) {
            if(releaseId != null) {
                rVo.setReleaseId(Integer.parseInt(releaseId));
                result = rSvc.updateReleaseContentById(rVo);
            }

            out.println(result);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(required = false) String newArticle,
                          @RequestParam(required = false) String type,
                          @RequestParam(required = false) String releaseId) throws ServletException, IOException {
        doGet(request, response, newArticle, type, releaseId);
    }
}
