package pers.husen.web.controller.article;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.old_service.CodeLibrarySvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/code/upload.hms")
public class CodeUploadController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String newArticle,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) Integer articleId) throws IOException {
        // 转化为json
        JSONObject jsonObject = JSONObject.fromObject(newArticle);
        // 转化为bean
        CodeLibraryVo cVo = TypeConvertHelper.jsonObj2CodeBean(jsonObject);
        // 如果不是以逗号分隔的，关键字之间的多个空格都处理为一个
        String codeLabel = cVo.getCodeLabel();
        if (codeLabel.indexOf(CommonConstants.ENGLISH_COMMA) == -1
                && codeLabel.indexOf(CommonConstants.CHINESE_COMMA) == -1) {
            cVo.setCodeLabel(codeLabel.replaceAll("\\s+", " "));
        }
        if (codeLabel.indexOf(CommonConstants.CHINESE_COMMA) != -1) {
            cVo.setCodeLabel(codeLabel.replace("，", ","));
        }

        CodeLibrarySvc cSvc = new CodeLibrarySvc();
        PrintWriter out = response.getWriter();

        /** 如果是修改代码 */
        if (type != null && RequestConstants.REQUEST_TYPE_MODIFY.equals(type)) {
            // 设置id
            cVo.setCodeId(articleId);

            int resultInsert = cSvc.updateCodeById(cVo);
            out.println(resultInsert);

            return;
        }
        /** 如果是上传新代码 */
        if (RequestConstants.REQUEST_TYPE_CREATE.equals(type)) {
            int insertResult = cSvc.insertCodeLibrary(cVo);
            out.println(insertResult);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String newArticle,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) Integer articleId) throws IOException {
        doGet(request, response, newArticle, type, articleId);
    }
}
