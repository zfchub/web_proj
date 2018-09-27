package pers.husen.web.controller.article;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.BlogArticleSvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/blog/upload.hms")
public class BlogUploadController {

    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String newArticle,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) Integer articleId) throws IOException {
        // 获取文章细节, 转化为json
        JSONObject jsonObject = JSONObject.fromObject(newArticle);
        // 转化为bean
        BlogArticleVo bVo = TypeConvertHelper.jsonObj2BlogBean(jsonObject);
        // 如果不是以逗号分隔的，关键字之间的多个空格都处理为一个
        String blogLabel = bVo.getBlogLabel();
        if (blogLabel.indexOf(CommonConstants.ENGLISH_COMMA) == -1
                && blogLabel.indexOf(CommonConstants.CHINESE_COMMA) == -1) {
            bVo.setBlogLabel(blogLabel.replaceAll("\\s+", " "));
        }
        if (blogLabel.indexOf(CommonConstants.CHINESE_COMMA) != -1) {
            bVo.setBlogLabel(blogLabel.replace("，", ","));
        }

        BlogArticleSvc bSvc = new BlogArticleSvc();
        PrintWriter out = response.getWriter();

        /** 如果是修改博客 */
        if (RequestConstants.REQUEST_TYPE_MODIFY.equals(type)) {
            // 设置id
            bVo.setBlogId(articleId);

            int insertResult = bSvc.updateBlogById(bVo);
            out.println(insertResult);

            return;
        }
        /** 如果是上传新博客 */
        if (RequestConstants.REQUEST_TYPE_CREATE.equals(type)) {
            int resultInsert = bSvc.insertBlogArticle(bVo);
            out.println(resultInsert);

            return;
        }
    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String newArticle,
                      @RequestParam(required = false) String uploadType,
                      @RequestParam(required = false) Integer articleId) throws IOException {
        doGet(request, response, newArticle, uploadType, articleId);
    }
}
