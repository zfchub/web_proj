package pers.husen.web.old_service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.old_dao.MessageAreaDao;
import pers.husen.web.old_dao.impl.MessageAreaDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月25日
 */
public class MessageAreaSvc implements MessageAreaDao{
	private MessageAreaDaoImpl mImpl = new MessageAreaDaoImpl();
	
	@Override
	public ArrayList<MessageAreaVo> queryAllMessageArea(int messageId) {
		return mImpl.queryAllMessageArea(messageId);
	}
	
	@Override
	public int insertMessageNew(MessageAreaVo mVo) {
		return mImpl.insertMessageNew(mVo);
	}
}
