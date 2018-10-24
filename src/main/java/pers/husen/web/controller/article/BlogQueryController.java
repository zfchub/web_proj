package pers.husen.web.controller.article;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.old_service.BlogArticleSvc;

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
@RequestMapping("/blog/query.hms")
public class BlogQueryController {

    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String keywords,
                      @RequestParam(required = false) String category,
                      @RequestParam(required = false) Integer blogId,
                      @RequestParam(required = false) Integer pageSize,
                      @RequestParam(required = false) Integer pageNo
                      ) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        BlogArticleSvc bSvc = new BlogArticleSvc();
        keywords = (keywords == null ? "" : URLDecoder.decode(keywords, "utf-8"));

        BlogArticleVo bVo = new BlogArticleVo();
        bVo.setBlogTitle(keywords);
        if (category != null && category.trim() != "") {
            bVo.setBlogCategory(Integer.parseInt(category));
        } else {
            bVo.setBlogCategory(-1);
        }

        /** 如果是请求查询总共数量 */
        String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
        if (queryTotalCount.equals(type)) {
            int count = bSvc.queryBlogTotalCount(bVo);
            out.println(count);

            return;
        }
        /** 如果是请求查询某一页的博客 */
        String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
        if (queryOnePage.equals(type)) {
            ArrayList<BlogArticleVo> bVos = bSvc.queryBlogArticlePerPage(bVo, pageSize, pageNo);
            String json = JSONArray.fromObject(bVos).toString();

            out.println(json);

            return;
        }
        /** 如果是请求查询所有博客 */
        String queryAllBlog = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
        if (queryAllBlog.equals(type)) {
            ArrayList<BlogArticleVo> aVos = bSvc.queryBlogArticles();
            String json = JSONArray.fromObject(aVos).toString();

            out.println(json);

            return;
        }
        /** 如果是请求查询上一篇有效博客 */
        String queryPrevious = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_PREVIOUS;
        if (queryPrevious.equals(type)) {
            bVo = bSvc.queryPreviousBlog(blogId);

            int previousBlog = 0;
            if (bVo != null && bVo.getBlogId() != 0) {
                previousBlog = bVo.getBlogId();
            }

            out.println(previousBlog);

            return;
        }
        /** 如果是请求查询下一篇有效博客 */
        String queryNext = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_NEXT;
        if (queryNext.equals(type)) {
            bVo = bSvc.queryNextBlog(blogId);

            int nextBlog = 0;
            if (bVo != null && bVo.getBlogId() != 0) {
                nextBlog = bVo.getBlogId();
            }

            out.println(nextBlog);

            return;
        }

    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String keywords,
                      @RequestParam(required = false) String category,
                      @RequestParam(required = false) Integer blogId,
                      @RequestParam(required = false) Integer pageSize,
                      @RequestParam(required = false) Integer pageNo
    ) throws IOException {
        doGet(request, response, type, keywords, category, blogId, pageSize, pageNo);
    }

}
