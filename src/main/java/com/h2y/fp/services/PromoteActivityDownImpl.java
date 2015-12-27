package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;

/**
 * 项目名称：h2yfp Maven Webapp  
 * 类名称：PromoteActivityDownImpl  
 * 类描述：推广活动图片链接  
 * 创建人：侯飞龙  
 * 创建时间：2015年8月18日 下午10:13:45  
 * 修改人：侯飞龙
 * 修改时间：2015年8月18日 下午10:13:45  
 * 修改备注：  
 * @version
 */
@Service(value="promoteActivityDown")
public class PromoteActivityDownImpl implements IFileDownService {

	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		Map<String,Object> fileData = fileDataDao.getPromoteActivity(Long.valueOf(id));
		if (null==fileData) {
			return null;
		}
		
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(fileData.get("root_path")+""+fileData.get("relative_path")+"/"+fileData.get("disk_file_name"));
		fileDownMode.setSaveName(fileData.get("disk_file_name")+"");
		return fileDownMode;
	}
}
