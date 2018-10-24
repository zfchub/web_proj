package pers.husen.web.unit;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.dao.ArticleCategoryMapper;

import java.util.Date;
import java.util.List;

public class ArticleCategoryDaoTest extends TestBase{

    @Autowired(required = false)
    private ArticleCategoryMapper articleCategoryMapper;

    private ArticleCategoryVo acVo;

    @Before
    public void before() {
        acVo = new ArticleCategoryVo();
        acVo.setCategoryName("testTest");
        acVo.setCreateDate(new Date());
        acVo.setCategoryDelete("0");
    }

    @Test
    public void test() {
        articleCategoryMapper.insertCategory(acVo);
        ArticleCategoryVo vo = articleCategoryMapper.queryById(acVo.getCategoryId());
        assert vo.getCategoryName().equalsIgnoreCase(acVo.getCategoryName());
        articleCategoryMapper.deleteById(acVo.getCategoryId());
        ArticleCategoryVo voNew = articleCategoryMapper.queryById(acVo.getCategoryId());
        assert voNew == null;
        List<ArticleCategoryVo> list = articleCategoryMapper.queryAllCategory();
        assert list.size() == 5;
    }
}
