package pers.husen.web.old_dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.common.helper.DateFormatHelper;
import pers.husen.web.old_dao.VisitTotalDao;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.DbManipulationUtils;

/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public class VisitTotalDaoImpl implements VisitTotalDao {
	@Override
	public int queryVisitTotal() {
		String sql = "SELECT visit_count as count FROM visit_total WHERE visit_id = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(1);
		
		return DbQueryUtils.queryIntByParam(sql, paramList);
	}
	
	@Override
	public int queryVisitToday() {
		String sql = "SELECT visit_count as count FROM visit_total WHERE visit_date = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(DateFormatHelper.formatDateYMD());
		
		return DbQueryUtils.queryIntByParam(sql, paramList);
	}

	@Override
	public int updateVisitCount() {
		// 历史总访问量加1
		String sqlTotal = "UPDATE visit_total SET visit_count = (( SELECT selTmp.visit_count FROM (select tmp.* from visit_total tmp) AS selTmp WHERE visit_id = ? ) + 1) WHERE visit_id = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(1);
		paramList.add(1);
		DbManipulationUtils.updateRecordByParam(sqlTotal, paramList);
		paramList.clear();
		
		Date currentDate = DateFormatHelper.formatDateYMD();
		String sqlQueryTest = "SELECT count(*) as count FROM visit_total WHERE visit_date = ?";
		paramList.add(currentDate);
		int result = DbQueryUtils.queryIntByParam(sqlQueryTest, paramList);
		
		if(result == -1) {
			return -1;
		}

		// 今日总访问量初始为1
		if(result == 0) {
			String insertSql = "INSERT INTO visit_total (visit_date, visit_count) VALUES (?, ?)";
			paramList.add(1);
			return DbManipulationUtils.insertNewRecord(insertSql, paramList);
		}

		// 今日总访问量加1
		String sqlToday = "UPDATE visit_total SET visit_count = (( SELECT selTmp.visit_count FROM (select tmp.* from visit_total tmp) AS selTmp WHERE visit_date = ?) + 1) WHERE visit_date = ?";
		paramList.add(currentDate);
		return DbManipulationUtils.updateRecordByParam(sqlToday, paramList);
	}
}