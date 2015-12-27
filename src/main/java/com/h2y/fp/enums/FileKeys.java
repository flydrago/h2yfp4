package com.h2y.fp.enums;

/**
 * 参数处理

 * @author：段晓刚

 * @update：2014年10月31日 下午4:22:32

 * @Email：
 */
public enum FileKeys {
	
	key;
	
	public enum FUPKey {
		//类型
		session("session"),
		//编码
		code("code"),
		//地址
		path("path"),
		//时间
		time("time");
		
		private String _value = "";
		
		private FUPKey(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
	}
	
	public enum FType {
		//类型
		notice("notice");
		
		private String _value = "";
		
		private FType(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
	}
	
	public enum FUPResult {
		//结果标示
		rtflg("rtflg"),
		rtfilename("rtfilename"),
		rtsavepath("rtsavepath"),
		rtsavename("rtsavename");
		
		private String _value = "";
		
		private FUPResult(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
	}
	
	public enum FUPVResult {
		//结构
		success("success"),
		fail("fail"),
		error("error");
		
		private String _value = "";
		
		private FUPVResult(String value){
			_value = value;
		}
		
		public String value(){
			return _value;
		}
	}
}