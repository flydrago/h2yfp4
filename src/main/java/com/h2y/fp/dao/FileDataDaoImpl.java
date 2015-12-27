package com.h2y.fp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.fp.dao.read.IFileDataDaoR;
import com.h2y.fp.dao.write.IFileDataDaoW;

@Service("fileDataDao")
public class FileDataDaoImpl implements IFileDataDao {

	@Autowired
	protected IFileDataDaoR fileDataDaoR;
	@Autowired
	protected IFileDataDaoW fileDataDaoW;

	public Map<String, Object> getFileData(long id) {
		return fileDataDaoR.getFileData(id);
	}

	public Map<String, Object> getCommonSubject(long id) {
		return fileDataDaoR.getCommonSubject(id);
	}

	public List<Map<String, Object>> getGoodsTypeLogoList(long typeId) {
		return fileDataDaoR.getGoodsTypeLogoList(typeId);
	}

	public Map<String, Object> getCommonImage(long id) {
		return fileDataDaoR.getCommonImage(id);
	}

	public Map<String, Object> getFindService(long id) {
		return fileDataDaoR.getFindService(id);
	}

	public List<Map<String, Object>> getSysUnitsService(long id) {
		return fileDataDaoR.getSysUnitsService(id);
	}

	public Map<String, Object> getFindActivity(long id) {
		return fileDataDaoR.getFindActivity(id);
	}

	public Map<String, Object> getAdvertSubject(long id) {
		return fileDataDaoR.getAdvertSubject(id);
	}

	public List<Map<String, Object>> getLocalService(long id) {
		return fileDataDaoR.getLocalService(id);
	}

	public Map<String, Object> getPromoteActivity(long id) {
		return fileDataDaoR.getPromoteActivity(id);
	}

	public Map<String, Object> getShareHref(long id) {
		return fileDataDaoR.getShareHref(id);
	}

	public Map<String, Object> getGoodsMarkInfo(long id) {
		return fileDataDaoR.getGoodsMarkInfo(id);
	}

	public Map<String, Object> getVoteSubject(long id) {
		return fileDataDaoR.getVoteSubject(id);
	}

	public Map<String, Object> getVoteSubjectFileBySubjectId(
			Map<String, Object> map) {
		return fileDataDaoR.getVoteSubjectFileBySubjectId(map);
	}

	public Map<String, Object> getGoodsLogoDataByDataId(Map<String, Object> map) {

		return fileDataDaoR.getGoodsLogoDataByDataId(map);
	}

	public Map<String, Object> getAdvertSubjectInfoRtByDataId(long id) {
		return fileDataDaoR.getAdvertSubjectInfoRtByDataId(id);
	}

}
