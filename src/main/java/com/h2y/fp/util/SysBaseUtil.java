package com.h2y.fp.util;

import com.h2y.security.MD5Util;
import com.h2y.util.PropertiesUtil;

public class SysBaseUtil {

	/**
	 * mencached 到期时间
	 */
	public final static long EXPIRY_TIME = 1800;
	
	/**
	 * 0：不用缓存、1：使用memcached
	 */
	public static int SERVER_CACHE = 0;
	
	
	static {

		PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance("/confing.properties");
		if (mPropertiesUtil.getValueByKey("sv.cache") != null) {
			SERVER_CACHE = Integer.parseInt(mPropertiesUtil.getValueByKey("sv.cache"));
		}
	}


	public static void main(String[] args) {


		//		Calendar nowTime = Calendar.getInstance();
		//		long now = nowTime.getTimeInMillis();
		//		
		//		nowTime.add(Calendar.MINUTE, 30);
		//		long expiryTime = nowTime.getTimeInMillis();
		//		System.out.println("now:"+new Date().getTime());
		//		System.out.println("now:"+now+" expiryTime:"+(expiryTime-now)/60000);


		//		String prefix = "12_123123_2323";
		//		prefix = prefix.substring(prefix.indexOf("_")+1, prefix.length());
		//		System.out.println(prefix);
		//		System.out.println(prefix.replaceAll("_", ","));

		//		Map<String,Object> map = new HashMap<String,Object>();
		//		map.put("code", 1);
		//		map.put("value", "男");
		//
		//		Map<String,Object> map1 = new HashMap<String,Object>();
		//		map1.put("code", 1);
		//		map1.put("value", "女");
		//
		//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//
		//		list.add(map1);
		//		list.add(map);
		//
		//		System.out.println(JSONUtil.getJson(list));

//
//		File filePath = new File("/opt1/test1/");
//		if (!filePath.exists()) {
//			filePath.mkdirs();
//		}
		System.out.println(MD5Util.getMD5("123"));
	}



	/**
	 * 侯飞龙
	 * 混合选择窗口，列表健
	 */
	public enum MixSelectListKey{

		/**
		 * 人员列表健
		 */
		pepleKey("p_"),

		/**
		 * 部门列表健
		 */
		deptKey("d_");

		public String key;
		private MixSelectListKey(String result){
			this.key=result;
		}
	}


	/**
	 * 权限类型静态变量
	 */
	public class PrivilegeKey{

		/**
		 * 单位
		 */
		public final static String UNIT = "UNIT";


		/**
		 * 用户
		 */
		public final static String USER = "USER";

		/**
		 * 角色
		 */
		public final static String ROLE = "ROLE";

		/**
		 * 菜单
		 */
		public final static String MENU = "MENU";


		/**
		 * 按钮
		 */
		public final static String BUTTON = "BUTTON";
	}

	/**
	 * mencached健前缀
	 */
	public class MemcachedKeyPrefix{

		/**
		 * 用户Id
		 */
		public final static String USER_ID = "H2Y_USER_ID";


		/**
		 * 单位Id
		 */
		public final static String UNIT_ID = "H2Y_UNIT_ID";

		/**
		 * 系统角色Id
		 */
		public final static String SYS_ROLE_ID = "H2Y_SYS_ROLE_ID";


		/**
		 * 用户对象
		 */
		public final static String USER = "H2Y_USER";

		/**
		 * 单位对象
		 */
		public final static String UNIT = "H2Y_UNIT";
	}


	/**
	 * 系统维护关联类型
	 */
	public class SysJoinType{


		/**
		 * 菜单
		 */
		public final static String MENU = "menu";


		/**
		 * 表格列
		 */
		public final static String GRID = "grid";

		/**
		 * 验证
		 */
		public final static String VALIDATE = "validate";


		/**
		 * 查询
		 */
		public final static String QUERY = "query";
	}


	/**
	 * 字典前缀 
	 */
	public class DictPrefix{

		/**
		 * 主表
		 */
		public final static String DIC_MAIN = "DIC_MAIN";


		/**
		 * 详细表
		 */
		public final static String DIC_DETAIL = "DIC_DETAIL";
	}


	public enum DictClumn{
		//id
		id,
		//主表Id
		dictMainId,
		//编码
		code,
		//对应值
		value,
		//备注信息
		memo,
		//排序字段
		ord;
	}

	public enum DictOrderBy{
		//降序
		desc,
		//升序
		asc
	}


	/**
	 * 操作类型
	 */
	public enum OpType{

		/**
		 * 登陆
		 */
		login,

		/**
		 * 退出
		 */
		loginOut,

		/**
		 * 添加
		 */
		add,

		/**
		 * 删除
		 */
		delete,

		/**
		 * 修改
		 */
		modify,

		/**
		 * 查询
		 */
		search;
	}

	/**
	 * 操作结果
	 */
	public enum OpRresult{

		/**
		 * 操作成功
		 */
		success,

		/**
		 * 操作失败
		 */
		fail;
	}
}
