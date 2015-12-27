package com.h2y.fp.dao;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：h2yfp2  
 * 类名称：IFileDataDao  
 * 类描述：  获取下载图片的数据库操作接口
 * 创建人：侯飞龙  
 * 创建时间：2015年4月2日 上午9:30:20  
 * 修改人：侯飞龙
 * 修改时间：2015年4月2日 上午9:30:20  
 * 修改备注：  
 * @version
 */
public interface IFileDataDao{

	/**
	 * 得到商品的Logo和快照信息
	 * @param id 主键
	 * @return
	 */
	public Map<String,Object> getFileData(long id);

	/**
	 * 得到促销活动主题信息
	 * @param id 主题Id
	 * @return
	 */
	public Map<String,Object> getCommonSubject(long id);


	/**
	 * 根据类型Id，得到商品类型Logo列表
	 * @param typeId 类型Id
	 * @return
	 */
	public List<Map<String,Object>> getGoodsTypeLogoList(long typeId);

	/**
	 * 根据Id，得到图片信息（启动加载图片等）
	 * @param id
	 * @return
	 */
	public Map<String,Object> getCommonImage(long id);

	/**
	 * 根据Id，得到发现服务信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> getFindService(long id);

	/**
	 * 根据公司id获取公司图片
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getSysUnitsService(long id);

	/**
	 * 获取小达快报图片
	 * @param id
	 * @return
	 */
	public Map<String,Object> getFindActivity(long id);


	/**
	 * 得到广告主题
	 * @param id 主题id
	 * @return
	 */
	public Map<String,Object> getAdvertSubject(long id);


	/**
	 * 获取本地服务门店图片
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getLocalService(long id);


	/**
	 * 根据id，得到对应的推广活动
	 * @param id
	 * @return
	 */
	public Map<String,Object> getPromoteActivity(long id);

	/**
	 * 根据id，得到对应的分享链接
	 * @param id
	 * @return
	 */
	public Map<String,Object> getShareHref(long id);


	/**
	 * 根据id，得到标签详细图片
	 * @param id
	 * @return
	 */
	public Map<String,Object> getGoodsMarkInfo(long id);

	/**
	 * 根据id，得到对应的投票主题
	 * @param id
	 * @return
	 */
	public Map<String,Object> getVoteSubject(long id);


	/**
	 * 根据投票主题id，和 主题类型得到对应的图片信息
	 * @param map
	 * {subjectId:主题id,
	 * fileType:文件类型  0：主页}
	 * @return
	 */
	public Map<String,Object> getVoteSubjectFileBySubjectId(Map<String,Object> map);


	/**
	 * 得到商品定价id和版本号得到对应的Logo图片
	 * @param map
	 * {dataId:定价id,
	 * dataVersion:定价版本号}
	 * @return
	 */
	public Map<String,Object> getGoodsLogoDataByDataId(Map<String,Object> map);

	/**
	 * 获取广告主题头部图片及分享图片
	 * @param map
	 * @return
	 */
	public Map<String,Object> getAdvertSubjectInfoRtByDataId(long id);


}