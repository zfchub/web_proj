package pers.husen.web.old_dao;


/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public interface VisitTotalDao {
	/**
	 * 查询历史总访问量
	 * @return
	 */
	public int queryVisitTotal();
	
	/**
	 * 查询当日访问量
	 * @return
	 */
	public int queryVisitToday();
	
	/**
	 * 更新当日访问量和总访问量
	 * 
	 * @return
	 */
	public int updateVisitCount();
}
