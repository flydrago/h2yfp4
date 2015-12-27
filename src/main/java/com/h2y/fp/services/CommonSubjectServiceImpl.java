package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;
import com.h2y.util.FileServiceUtil;

@Service(value="commonSubjectService")
public class CommonSubjectServiceImpl implements IFileDownService {
	
	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		Map<String,Object> subjectInfo = fileDataDao.getCommonSubject(Long.valueOf(id));
		String os = request.getParameter("os");
		
		Object androidFileName = subjectInfo.get("android_file_name");
		Object iosFileName = subjectInfo.get("ios_file_name");
		Object wechatFileName = subjectInfo.get("wechat_file_name");
		
		String saveName = FileServiceUtil.getOsSaveName(os, androidFileName, iosFileName, wechatFileName);
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(subjectInfo.get("root_path")+""+subjectInfo.get("relative_path")+saveName);
		fileDownMode.setSaveName(saveName);
		return fileDownMode;
	}
}
