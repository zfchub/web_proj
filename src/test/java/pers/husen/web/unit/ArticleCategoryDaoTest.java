package pers.husen.web.unit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.dao.ArticleCategoryMapper;

import java.util.Date;

public class ArticleCategoryDaoTest extends TestBase{

    @Autowired(required = false)
    private ArticleCategoryMapper articleCategoryMapper;

    @Test
    public void test() {
        ArticleCategoryVo acVo = new ArticleCategoryVo();
        acVo.setCategoryName("test");
        acVo.setCreateDate(new Date());
        acVo.setCategoryDelete("0");
        articleCategoryMapper.insertCategory(acVo);
    }
}
