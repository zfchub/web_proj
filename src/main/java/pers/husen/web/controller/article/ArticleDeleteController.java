package pers.husen.web.controller.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.old_service.BlogArticleSvc;
import pers.husen.web.old_service.CodeLibrarySvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/article/delete.hms")
public class ArticleDeleteController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) Integer blogId,
                      @RequestParam(required = false) Integer codeId) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String requestType = type;
        /** 如果是删除博客 */
        String logicDeleteBlog = RequestConstants.REQUEST_TYPE_LOGIC_DELETE + RequestConstants.MODE_BLOG;
        if(logicDeleteBlog.equals(requestType)) {
            BlogArticleSvc bSvc = new BlogArticleSvc();
            int result = bSvc.logicDeleteBlogById(blogId);

            out.println(result);

            return;
        }
        /** 如果是删除代码 */
        String logicDeleteCode = RequestConstants.REQUEST_TYPE_LOGIC_DELETE + RequestConstants.MODE_CODE;
        if(logicDeleteCode.equals(requestType)) {
            CodeLibrarySvc cSvc = new CodeLibrarySvc();
            int result = cSvc.logicDeleteCodeById(codeId);

            out.println(result);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) Integer blogId,
                       @RequestParam(required = false) Integer codeId)
            throws ServletException, IOException {
        doGet(request, response, type, blogId, codeId);
    }
}
