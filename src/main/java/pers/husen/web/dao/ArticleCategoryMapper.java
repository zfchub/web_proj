package pers.husen.web.dao;

import org.apache.ibatis.annotations.Param;
import pers.husen.web.bean.vo.ArticleCategoryVo;

import java.util.List;

public interface ArticleCategoryMapper {

    /**
     * @param aVo 文章分类
     * @return 主键id
     */
    void insertCategory(ArticleCategoryVo aVo);

    /**
     * 根据分类id查询分类
     * @param categoryId
     */
    ArticleCategoryVo queryById(@Param("categoryId") Integer categoryId);

    /**
     * 根据分类id删除分类
     * @param categoryId
     */
    void deleteById(@Param("categoryId") Integer categoryId);

    /**
     * 查询所有分类
     * @return
     */
    List<ArticleCategoryVo> queryAllCategory();

    /**
     * 根据文章类别查询分类和对应的数量
     * @param classification
     * @return
     */
    List<ArticleCategoryVo> queryCategory3Num(String classification);
}
