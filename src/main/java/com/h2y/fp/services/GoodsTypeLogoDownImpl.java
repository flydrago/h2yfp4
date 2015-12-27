package com.h2y.fp.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;

@Service(value="goodsTypeLogoDown")
public class GoodsTypeLogoDownImpl implements IFileDownService {
	
	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		List<Map<String,Object>> logoList = fileDataDao.getGoodsTypeLogoList(Long.valueOf(id));
		
		if (null==logoList || logoList.isEmpty()) {
			return null;
		}
		
		Map<String,Object> logoInfo = logoList.get(0);
		String type = request.getParameter("type");
		String saveName = "";
		
		Object androidFileName = logoInfo.get("android_file_name");
		Object iosFileName = logoInfo.get("ios_file_name");
		Object wechatFileName = logoInfo.get("wechat_file_name");
		
		if ("android".equals(type)) {
			
			if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else if (null!=iosFileName && !"".equals(iosFileName)) {
				saveName = iosFileName+"";
			}else{
				saveName = wechatFileName+"";
			}
		}else if ("ios".equals(type)) {
			
			if (null!=iosFileName && !"".equals(iosFileName)) {
				saveName = iosFileName+"";
			}else if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else{
				saveName = wechatFileName+"";
			}
		}else if ("wechat".equals(type)) {
			
			if (null!=wechatFileName && !"".equals(wechatFileName)) {
				saveName = wechatFileName+"";
			}else if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else{
				saveName = iosFileName+"";
			}
		}
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(logoInfo.get("root_path")+""+logoInfo.get("relative_path")+saveName);
		fileDownMode.setSaveName(saveName);
		return fileDownMode;
	}
}
