package com.video.transcoder.framework.vo;

import java.util.HashMap;
import java.util.Map;

public class ResponseBody {

	private Map<String,Object> pageMap= new HashMap<>();

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}
	
	
}
