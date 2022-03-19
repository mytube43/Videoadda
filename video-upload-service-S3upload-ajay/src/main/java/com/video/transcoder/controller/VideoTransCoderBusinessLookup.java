package com.video.transcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.video.transcoder.servie.IStorageService;

import lombok.Data;

@Component 
@Data
public class VideoTransCoderBusinessLookup {

	@Autowired
	IStorageService storageService;
	
	
	public IStorageService getStorageService() {
		return this.storageService;
	}
}
