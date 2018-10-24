package pers.husen.web.controller.category;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.old_service.ArticleCategorySvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * user: zhoufangchao
 * date: 2018/9/27
 */
@Controller
@RequestMapping("/category.hms")
public class CategoryController {
    private static final long serialVersionUID = 1L;

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String cateName,
                      @RequestParam(required = false) String classify
                      ) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        ArticleCategorySvc aSvc = new ArticleCategorySvc();

        /** 如果是创建新的分类 **/
        if (RequestConstants.REQUEST_TYPE_CREATE.equals(type)) {
            ArticleCategoryVo aVo = new ArticleCategoryVo();
            aVo.setCategoryName(cateName);
            aVo.setCreateDate(new Date());

            int resultInsert = aSvc.insertCategory(aVo);

            if (resultInsert == 1) {
                int curCateId = aSvc.queryMaxId();
                out.println(curCateId);

                return;
            }
        }

        /** 如果是查询文章分类（数量不为0的） **/
        String queryCategory = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_CATEGORY;
        if (queryCategory.equals(type)) {
            ArrayList<ArticleCategoryVo> aVos = aSvc.queryCategory3Num(classify);
            String json = JSONArray.fromObject(aVos).toString();

            out.println(json);

            return;
        }

        /** 如果是查询所有分类 **/
        String queryAll = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
        if(queryAll.equals(type)) {
            ArrayList<ArticleCategoryVo> aVos = aSvc.queryAllCategory();
            String json = JSONArray.fromObject(aVos).toString();

            out.println(json);

            return;
        }

    }

    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(required = false) String type,
                      @RequestParam(required = false) String cateName,
                      @RequestParam(required = false) String classify
    ) throws IOException {
        doGet(request, response, type, cateName, classify);
    }
}
