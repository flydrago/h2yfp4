package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;
import com.h2y.util.FileServiceUtil;

/**
 * 项目名称：h2yfp2  
 * 类名称：CommnImageServiceImpl  
 * 类描述：图片维护服务  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月11日 上午9:22:28  
 * 修改人：侯飞龙
 * 修改时间：2015年5月11日 上午9:22:28  
 * 修改备注：  
 * @version
 */
@Service(value="commonImageService")
public class CommnImageServiceImpl implements IFileDownService {
	
	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		
		Map<String,Object> logoInfo = fileDataDao.getCommonImage(Long.valueOf(id));
		
		if (null==logoInfo) {
			return null;
		}
		
		String os = request.getParameter("os");
		Object androidFileName = logoInfo.get("android_file_name");
		Object iosFileName = logoInfo.get("ios_file_name");
		Object wechatFileName = logoInfo.get("wechat_file_name");
		String saveName = FileServiceUtil.getOsSaveName(os, androidFileName, iosFileName, wechatFileName);
		
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(logoInfo.get("root_path")+""+logoInfo.get("relative_path")+saveName);
		fileDownMode.setSaveName(saveName);
		return fileDownMode;
	}
}
