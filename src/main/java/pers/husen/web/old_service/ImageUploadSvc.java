package pers.husen.web.old_service;

import pers.husen.web.bean.vo.ImageUploadVo;
import pers.husen.web.old_dao.ImageUploadDao;
import pers.husen.web.old_dao.impl.ImageUploadDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年10月20日
 */
public class ImageUploadSvc implements ImageUploadDao{
	private ImageUploadDaoImpl iImpl = new ImageUploadDaoImpl();
	
	@Override
	public int insertImageUpload(ImageUploadVo iVo) {
		return iImpl.insertImageUpload(iVo);
	}
}