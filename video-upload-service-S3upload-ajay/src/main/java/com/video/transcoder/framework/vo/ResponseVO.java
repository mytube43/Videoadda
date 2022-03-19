package com.video.transcoder.framework.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseVO {

	@JsonProperty("ResponseInfo")
	private ResponseInfo responseInfo;
	
	@JsonProperty("ResponseBody")
	private ResponseBody responseBody;
	
	public ResponseVO(ResponseInfo responseInfo,ResponseBody responseBody) {
		this.responseInfo=responseInfo;
		this.responseBody = responseBody;
	}

	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}

	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	
	
}
