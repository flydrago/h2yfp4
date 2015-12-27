package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;
import com.h2y.util.FileServiceUtil;

@Service(value="findServiceDown")
public class FindServiceDownImpl implements IFileDownService {
	
	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		
		Map<String,Object> logoInfo = fileDataDao.getFindService(Long.valueOf(id));
		
		if (null==logoInfo || logoInfo.isEmpty()) {
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
