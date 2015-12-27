package com.h2y.fp.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;

@Service(value="findActivityDown")
public class FindActivityDownImpl implements IFileDownService {

	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {


		Map<String,Object> logoInfo = fileDataDao.getFindActivity(Long.valueOf(id));

		if (null==logoInfo || logoInfo.isEmpty()) {
			return null;
		}

		String diskFileName = logoInfo.get("disk_file_name")+"";

		FileDownMode fileDownMode = new FileDownMode();
		fileDownMode.setFilePath(logoInfo.get("root_path")+""+logoInfo.get("relative_path")+diskFileName);
		fileDownMode.setSaveName(diskFileName);
		return fileDownMode;
	}
}
