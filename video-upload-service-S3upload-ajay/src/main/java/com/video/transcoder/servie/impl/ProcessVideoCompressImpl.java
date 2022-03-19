package com.video.transcoder.servie.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.video.transcoder.common.AbstractBusinessServiceHandler;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Component
public class ProcessVideoCompressImpl extends AbstractBusinessServiceHandler{

	@Value("mytube.video.upload.bucket.name")
	private String downloadBucket;
	
	
	public void downloadVideo(String videoFileName) {
		 try {
		videoFileName="Raindrops_Videvo_preview.webm";
		 GetObjectRequest getObjectRequest = GetObjectRequest.builder()
	                .bucket(downloadBucket)
	                .key("input/"+videoFileName)
	                .build();

		 ResponseInputStream<GetObjectResponse> response =getS3Client().
				 getObject(getObjectRequest);
		
			byte[] respByte = IOUtils.toByteArray(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeFileToClassPath(Optional<MultipartFile> videoFile) {
		if(videoFile.isPresent()) {
			MultipartFile uploadedFile = videoFile.get();
			File file = new File("classpath:resources/rawVideo/"+uploadedFile.getOriginalFilename());
			try {
			Path p =Files.write(file.toPath(),uploadedFile.getBytes());
			p.getFileName();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
