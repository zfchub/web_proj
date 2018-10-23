package pers.husen.web.dao;

import pers.husen.web.bean.vo.ArticleCategoryVo;

public interface ArticleCategoryMapper {

    /**
     * @param aVo 文章分类
     * @return 主键id
     */
    void insertCategory(ArticleCategoryVo aVo);
}
