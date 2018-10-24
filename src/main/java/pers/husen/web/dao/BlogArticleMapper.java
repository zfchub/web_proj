package pers.husen.web.dao;

import org.apache.ibatis.annotations.Param;
import pers.husen.web.bean.vo.BlogArticleVo;

import java.util.List;

public interface BlogArticleMapper {
    /**
     * 查询博客文章
     *
     * @return 博客文章数组
     */
    public List<BlogArticleVo> queryAllBlogArticles();

    /**
     * 根据条件询博客数量
     * @param bVo
     * @return
     */
    public int queryBlogTotalCount(BlogArticleVo bVo);

    /**
     * 按照页面大小和页码查询博客
     * @param blogDelete
     * @param blogTitle
     * @param blogCategory
     * @param pageSize
     * @param pageNo
     * @return
     */
    public List<BlogArticleVo> queryBlogArticlePerPage(@Param("blogDelete") Integer blogDelete, @Param("blogTitle") String blogTitle,
                                                       @Param("blogCategory") String blogCategory, @Param("pageSize") int pageSize,
                                                       @Param("pageNo") int pageNo);

    /**
     * 根据id查询单独的一篇博客
     *
     * @param blogId
     * @return 一篇博客
     */
    public BlogArticleVo queryPerBlogById(int blogId);

    /**
     * 插入记录
     *
     * @param bVo
     * @return 返回插入结果
     */
    public int insertBlogArticle(BlogArticleVo bVo);

    /**
     * 根据id更新博客阅读次数
     * @param blogId
     * @return
     */
    public int updateBlogReadById(int blogId);

    /**
     * 根据id修改博客内容
     * @param bVo
     * @return
     */
    public int updateBlogById(BlogArticleVo bVo);

    /**
     * 根据id逻辑删除博客
     * @param blogId
     * @return
     */
    public int logicDeleteBlogById(int blogId);

    /**
     * 根据id查找上一篇有效博客
     * @param blogId
     * @return
     */
    public BlogArticleVo queryPreviousBlog(int blogId);

    /**
     * 根据id查找下一篇有效博客
     * @param blogId
     * @return
     */
    public BlogArticleVo queryNextBlog(int blogId);

}
