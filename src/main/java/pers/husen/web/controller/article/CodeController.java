package pers.husen.web.controller.article;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.constants.ResponseConstants;
import pers.husen.web.common.helper.ReadH5Helper;
import pers.husen.web.old_service.CodeLibrarySvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/code.hms")
public class CodeController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) Integer codeId,
                      @RequestParam(required = false) String type) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        CodeLibrarySvc cSvc = new CodeLibrarySvc();

        CodeLibraryVo cVo = cSvc.queryPerCodeById(codeId);

        //判断是否是返回代码json数据
        String returnType = type;
        if(returnType != null && RequestConstants.REQUEST_TYPE_JSON.equals(returnType)) {
            out.println(JSONObject.fromObject(cVo));

            return;
        }

        /** 默认返回整篇文章 */
        response.setContentType("text/html");
        String resultHtml = ReadH5Helper.modifyHtmlKeywords(ResponseConstants.CODE_TEMPLATE_PATH, cVo.getCodeLabel());
        out.println(resultHtml);
        //增加访问次数
        cSvc.updateCodeReadById(codeId);
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) Integer codeId,
                      @RequestParam(required = false) String type) throws IOException {
        doGet(request, response, codeId, type);
    }
}
