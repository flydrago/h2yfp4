package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;


/**
 * 
 * @author sunfj
 * 广告主题获取 宣传页头部图片及分享图片
 * 
 *
 */
@Service(value="advertSubjectInfoRtService")
public class AdvertSubjectInfoRtServiceImpl implements IFileDownService {

	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		Map<String,Object> subjectInfo = fileDataDao.getAdvertSubjectInfoRtByDataId(Long.valueOf(id));
		//String os = request.getParameter("os");

		//Object androidFileName = subjectInfo.get("android_file_name");
		//Object iosFileName = subjectInfo.get("ios_file_name");
		//Object wechatFileName = subjectInfo.get("wechat_file_name");

		//String saveName = FileServiceUtil.getOsSaveName(os, androidFileName, iosFileName, wechatFileName);
		String saveName = subjectInfo.get("save_name")+""; 
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(subjectInfo.get("root_path")+""+subjectInfo.get("relative_path")+saveName);
		fileDownMode.setSaveName(saveName);

		return fileDownMode;
	}

}
