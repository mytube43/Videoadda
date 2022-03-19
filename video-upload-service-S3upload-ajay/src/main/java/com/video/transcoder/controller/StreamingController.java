package com.video.transcoder.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Amit
 *
 */

@Controller
@RequestMapping("/mytube/us")
public class StreamingController extends AbstractHelperController {

	@GetMapping(value="/getM3U8",produces = {"application/x-mpegURL"})
	public ResponseEntity<byte[]> getM3U8() {
		
		ResponseEntity<byte[]> response = null;

        final String home = System.getProperty("user.home");
        final File directory = new File("/home/amit" + File.separator + "Desktop/Song" + File.separator + "song_720p.m3u8");
		try {
			 InputStream inputStream = new FileInputStream(directory);
			byte[] m3u8File = IOUtils.toByteArray(inputStream);
			
			HttpHeaders headers = getCORSHeader();
			headers.add("Content-Disposition","attachment;filename=song_720p.m3u8");
			response = new ResponseEntity<byte[]>(m3u8File,headers,HttpStatus.OK);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return response;
        
	}
	
	
	@GetMapping(value="/{tsFileName}",produces = {"application/x-mpegURL"})
	public ResponseEntity<byte[]> getM3U8Files(@PathVariable String tsFileName) {

		ResponseEntity<byte[]> response = null;
        final File directory = new File("/home/amit" + File.separator 
        		+ "Desktop/Song" + File.separator + tsFileName);
		try {
			 InputStream inputStream = new FileInputStream(directory);
			byte[] m3u8File = IOUtils.toByteArray(inputStream);
			HttpHeaders headers = getCORSHeader();
			response = new ResponseEntity<byte[]>(m3u8File,headers,HttpStatus.OK);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return response;
	}
}
