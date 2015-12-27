package com.h2y.fp.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.IFileDataDao;
import com.h2y.fp.entity.FileDownMode;

@Service(value="sysUnitsServiceDown")
public class SysUnitsServiceDownImpl implements IFileDownService {

	@Autowired
	protected IFileDataDao fileDataDao;

	public FileDownMode getFileInfo(HttpServletRequest request, String id) {


		List<Map<String,Object>> list = fileDataDao.getSysUnitsService(Long.valueOf(id));

		if (null==list || list.isEmpty()) {
			return null;
		}else{
			Map<String,Object> fileData = list.get(0);
			FileDownMode fileDownMode = new FileDownMode();
			fileDownMode.setFilePath(fileData.get("root_path")+""+fileData.get("relative_path")+fileData.get("disk_file_name"));
			fileDownMode.setSaveName(fileData.get("disk_file_name")+"");
			return fileDownMode;
		}




	}
}
