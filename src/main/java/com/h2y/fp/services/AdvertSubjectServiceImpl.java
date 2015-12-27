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
 * 类名称：AdvertSubjectServiceImpl  
 * 类描述：广告栏位主题图片获取实现类  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月23日 下午1:56:43  
 * 修改人：侯飞龙
 * 修改时间：2015年4月23日 下午1:56:43  
 * 修改备注：  
 * @version
 */
@Service(value="advertSubjectService")
public class AdvertSubjectServiceImpl implements IFileDownService {
	
	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		Map<String,Object> subjectInfo = fileDataDao.getAdvertSubject(Long.valueOf(id));
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
