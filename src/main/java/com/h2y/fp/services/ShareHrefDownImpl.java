package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;

@Service(value="shareHrefDown")
public class ShareHrefDownImpl implements IFileDownService {

	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {
		
		Map<String,Object> fileData = fileDataDao.getShareHref(Long.valueOf(id));
		if (null==fileData) {
			return null;
		}
		
		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(fileData.get("root_path")+""+fileData.get("relative_path")+fileData.get("disk_file_name"));
		fileDownMode.setSaveName(fileData.get("disk_file_name")+"");
		return fileDownMode;
	}
}
