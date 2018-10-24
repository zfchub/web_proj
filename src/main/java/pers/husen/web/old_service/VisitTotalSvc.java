package pers.husen.web.old_service;

import pers.husen.web.old_dao.VisitTotalDao;
import pers.husen.web.old_dao.impl.VisitTotalDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public class VisitTotalSvc implements VisitTotalDao{
	private VisitTotalDaoImpl vImpl = new VisitTotalDaoImpl();
	
	@Override
	public int queryVisitTotal() {
		return vImpl.queryVisitTotal();
	}
	
	@Override
	public int queryVisitToday() {
		return vImpl.queryVisitToday();
	}
	
	@Override
	public int updateVisitCount() {
		return vImpl.updateVisitCount();
	}
}
