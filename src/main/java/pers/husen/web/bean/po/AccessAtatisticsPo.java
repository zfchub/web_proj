package pers.husen.web.bean.po;

import java.io.Serializable;

/**
 *访问统计
 *
 * @author 何明胜
 *
 * 2017年10月18日
 */
public class AccessAtatisticsPo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer accessToday;
	private Integer accessTotal;
	private Integer onlineCurrent;
	/**
	 * @return the accessToday
	 */
	public Integer getAccessToday() {
		return accessToday;
	}
	/**
	 * @param accessToday the accessToday to set
	 */
	public void setAccessToday(Integer accessToday) {
		this.accessToday = accessToday;
	}
	/**
	 * @return the accessTotal
	 */
	public Integer getAccessTotal() {
		return accessTotal;
	}
	/**
	 * @param accessTotal the accessTotal to set
	 */
	public void setAccessTotal(Integer accessTotal) {
		this.accessTotal = accessTotal;
	}
	/**
	 * @return the onlineCurrent
	 */
	public Integer getOnlineCurrent() {
		return onlineCurrent;
	}
	/**
	 * @param onlineCurrent the onlineCurrent to set
	 */
	public void setOnlineCurrent(Integer onlineCurrent) {
		this.onlineCurrent = onlineCurrent;
	}
}