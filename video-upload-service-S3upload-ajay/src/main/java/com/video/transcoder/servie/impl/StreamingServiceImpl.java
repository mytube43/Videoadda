package com.video.transcoder.servie.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.video.transcoder.servie.IStreamingService;

@Component
public class StreamingServiceImpl implements IStreamingService{

	public byte[] getM3U8playList(String fileName) {
		final String home = System.getProperty("user.home");
		final File directory = new File("/home/amit" + File.separator + "Desktop/Test" + File.separator + "720p.m3u8");
		try {
			InputStream inputStream = new FileInputStream(directory);
			byte[] m3u8File = IOUtils.toByteArray(inputStream);
			return m3u8File;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null; 
	}
}
