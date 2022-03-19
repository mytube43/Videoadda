package com.video.transcoder.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class ContentUploadReqVO extends VideoTransCoderReqVO {

	private String contentTitle;
	private String contentFormat;
	private List<String> contentTags;
	private String rawFileLoc;
	
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContentFormat() {
		return contentFormat;
	}
	public void setContentFormat(String contentFormat) {
		this.contentFormat = contentFormat;
	}
	public List<String> getContentTags() {
		return contentTags;
	}
	public void setContentTags(List<String> contentTags) {
		this.contentTags = contentTags;
	}
	public String getRawFileLoc() {
		return rawFileLoc;
	}
	public void setRawFileLoc(String rawFileLoc) {
		this.rawFileLoc = rawFileLoc;
	}
}
