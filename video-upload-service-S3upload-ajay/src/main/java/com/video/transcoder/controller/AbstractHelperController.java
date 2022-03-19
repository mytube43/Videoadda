package com.video.transcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.video.transcoder.framework.vo.ResponseBody;
import com.video.transcoder.framework.vo.ResponseInfo;
import com.video.transcoder.framework.vo.ResponseVO;


@Component
public abstract class AbstractHelperController {

	@Autowired
	protected VideoTransCoderBusinessLookup transcoderBusinessLookup;
	
	protected VideoTransCoderBusinessLookup getTranscoderBusinessLookup() {
		return this.transcoderBusinessLookup;
	}
	
	protected HttpHeaders getCORSHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
		headers.add("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, "
				+ "Origin,Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, Access-Control-Request-Headers");
		return headers;
	}
	
	protected ResponseVO getResponseError() {
		ResponseVO responseVO= new ResponseVO(new ResponseInfo(),new ResponseBody());
		responseVO.getResponseInfo().setResponseCode("1111");
		responseVO.getResponseInfo().setResponseStatus("Error");
		responseVO.getResponseBody().getPageMap().put("userMsg", "Unable to process your request right now!");
		return responseVO;
		}
	
}
