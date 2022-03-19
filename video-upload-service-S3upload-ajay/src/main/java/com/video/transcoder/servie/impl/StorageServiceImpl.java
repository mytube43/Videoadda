package com.video.transcoder.servie.impl;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.video.transcoder.common.AbstractBusinessServiceHandler;
import com.video.transcoder.common.TransCoderUtils;
import com.video.transcoder.dao.service.UploadDAOServiceImpl;
import com.video.transcoder.framework.vo.ResponseVO;
import com.video.transcoder.servie.IStorageService;
import com.video.transcoder.vo.ContentUploadReqVO;
import com.video.transcoder.vo.UploadFileInfoVO;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.UploadPartRequest;
import com.video.transcoder.framework.vo.*;

@Component
public class StorageServiceImpl extends AbstractBusinessServiceHandler implements IStorageService{

	private Region region = Region.AP_SOUTHEAST_1;
	
	@Value("${mytube.raw.content.location}")
	private String rawVideoLoc;
	
	@Value("${mytube.video.upload.bucket.name}")
	private String uploadBucket;
	
	
	public boolean isVideoUploaded(MultipartFile videoFile) {
		try {
		PutObjectRequest objectRequest = PutObjectRequest.builder()
	                .bucket(uploadBucket)
	                .key("input/"+videoFile.getOriginalFilename())
	                .build();
			PutObjectResponse response=	getS3Client().putObject(objectRequest, 
					RequestBody.fromBytes(videoFile.getBytes()));
			
			if(response.sdkHttpResponse().isSuccessful()) {
				System.out.println("Video Uploaded");
				return true;
			}
			} catch (AwsServiceException | SdkClientException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}
	
	public Optional<UploadFileInfoVO> writeFileToClassPath(Optional<MultipartFile> videoFile) {
		if(videoFile.isPresent()) {
			try {
				MultipartFile uploadedFile = videoFile.get();
				String FILE_NAME = rawVideoLoc+"/"+uploadedFile.getOriginalFilename();
				File file = new File(FILE_NAME);
				file.createNewFile();
				System.out.println("Absolute Path::  "+file.getAbsolutePath());
				Files.write(file.toPath(),uploadedFile.getBytes());
				UploadFileInfoVO fileInfoVO = new UploadFileInfoVO();
				fileInfoVO.setFileName(FILE_NAME);
				return Optional.ofNullable(fileInfoVO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(null);
	}
	
	public ResponseVO saveVideoMetaData(String metaDataString,String fileName) {
		String m3u8Location = "http://localhost:9001/hls/"+fileName+"playlist.m3u8";
		ContentUploadReqVO metaDataObj = (ContentUploadReqVO)TransCoderUtils.getObject(metaDataString,ContentUploadReqVO.class);
		metaDataObj.setRawFileLoc(fileName);
		Boolean response= getDaoService().getUploadDaoService().saveMetaData(metaDataObj);
		ResponseVO responseVO = getResponseVO();
		responseVO.getResponseInfo().setResponseCode("0000");
		responseVO.getResponseInfo().setResponseStatus("Success");
		Map<String,Object> pageMap = responseVO.getResponseBody().getPageMap();
		pageMap.put("userMessage", "Your file has been successfully uploaded");
		return responseVO;
		
	}
	
}
