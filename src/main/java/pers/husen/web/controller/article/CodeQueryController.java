package pers.husen.web.controller.article;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.service.CodeLibrarySvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * user: zhoufangchao
 * date: 2018/9/27
 */
@Controller
@RequestMapping("/code/query.hms")
public class CodeQueryController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String keywords,
                      @RequestParam(required = false) String category,
                      @RequestParam(required = false) Integer pageSize,
                      @RequestParam(required = false) Integer pageNo,
                      @RequestParam(required = false) Integer codeId) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        CodeLibrarySvc cSvc = new CodeLibrarySvc();
        keywords = (keywords == null ? "" : URLDecoder.decode(keywords,"utf-8"));

        CodeLibraryVo cVo = new CodeLibraryVo();
        cVo.setCodeTitle(keywords);
        if(category != null && category.trim() != "") {
            cVo.setCodeCategory(Integer.parseInt(category));
        }else {
            cVo.setCodeCategory(-1);
        }

        /** 如果是请求查询总共数量 */
        String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
        if (queryTotalCount.equals(type)) {
            int count = cSvc.queryCodeTotalCount(cVo);
            out.println(count);

            return;
        }
        /** 如果是查询某一页的代码 */
        String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
        if (queryOnePage.equals(type)) {

            ArrayList<CodeLibraryVo> cVos = cSvc.queryCodeLibraryPerPage(cVo, pageSize, pageNo);
            String json = JSONArray.fromObject(cVos).toString();

            out.println(json);

            return;
        }
        /** 如果是查询上一篇有效代码 */
        String queryPrevious = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_PREVIOUS;
        if (queryPrevious.equals(type)) {
            cVo = cSvc.queryPreviousCode(codeId);

            int previousCode = 0;
            if (cVo != null && cVo.getCodeId() != 0) {
                previousCode = cVo.getCodeId();
            }

            out.println(previousCode);

            return;
        }
        /** 如果是查询下一篇有效代码 */
        String queryNext = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_NEXT;
        if (queryNext.equals(type)) {
            cVo = cSvc.queryNextCode(codeId);

            int nextCode = 0;
            if (cVo != null && cVo.getCodeId() != 0) {
                nextCode = cVo.getCodeId();
            }

            out.println(nextCode);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) String keywords,
                       @RequestParam(required = false) String category,
                       @RequestParam(required = false) Integer pageSize,
                       @RequestParam(required = false) Integer pageNo,
                       @RequestParam(required = false) Integer codeId) throws IOException {
        doGet(request, response, type, keywords, category, pageSize, pageNo, codeId);
    }
}
