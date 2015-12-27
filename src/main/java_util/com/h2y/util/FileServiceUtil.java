package com.h2y.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：h2yfp2  
 * 类名称：FileServiceUtil  
 * 类描述：文件服务工具类  
 * 创建人：侯飞龙  
 * 创建时间：2015年5月11日 上午9:24:02  
 * 修改人：侯飞龙
 * 修改时间：2015年5月11日 上午9:24:02  
 * 修改备注：  
 * @version
 */
public class FileServiceUtil {

	/**
	 * 得到各个客户端的图片
	 * @param os 
	 * @param androidFileName
	 * @param iosFileName
	 * @param wechatFileName
	 * @return
	 */
	public static String getOsSaveName(String os,Object androidFileName,Object iosFileName,Object wechatFileName) {

		String saveName = null;
		if ("android".equals(os)) {
			
			if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else if (null!=iosFileName && !"".equals(iosFileName)) {
				saveName = iosFileName+"";
			}else{
				saveName = wechatFileName+"";
			}
		}else if ("IOS".equals(os)) {
			
			if (null!=iosFileName && !"".equals(iosFileName)) {
				saveName = iosFileName+"";
			}else if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else{
				saveName = wechatFileName+"";
			}
		}else if ("wechat".equals(os)) {
			
			if (null!=wechatFileName && !"".equals(wechatFileName)) {
				saveName = wechatFileName+"";
			}else if (null!=androidFileName && !"".equals(androidFileName)) {
				saveName = androidFileName+"";
			}else{
				saveName = iosFileName+"";
			}
		}else {
			return null;
		}
		return saveName;
	}
}
