package com.h2y.fp.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;
import com.h2y.util.MatcherUtil;

/**
 * 项目名称：h2yfp2  
 * 类名称：FileServiceImpl  
 * 类描述：获取商品的Logo  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月2日 上午9:34:08  
 * 修改人：侯飞龙
 * 修改时间：2015年4月2日 上午9:34:08  
 * 修改备注：  
 * @version
 */
@Service(value="goodsLogoService")
public class GoodsLogoServiceImpl implements IFileDownService {

	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		String gpId = request.getParameter("gpId");//商品定价id
		if (!MatcherUtil.checkNumber(gpId)) {
			return null;
		}
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dataId", Long.valueOf(gpId));
		Map<String,Object> fileData = fileDataDao.getGoodsLogoDataByDataId(params);
		if (null==fileData) {
			return null;
		}
		
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(fileData.get("root_path")+""+fileData.get("relative_path")+fileData.get("disk_file_name"));
		fileDownMode.setSaveName(fileData.get("disk_file_name")+"");
		return fileDownMode;
	}
}
