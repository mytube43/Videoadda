package com.video.transcoder.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.video.transcoder.framework.vo.ResponseBody;
import com.video.transcoder.framework.vo.ResponseInfo;
import com.video.transcoder.framework.vo.ResponseVO;
import com.video.transcoder.service.lookup.DAOServiceLookup;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public abstract class AbstractBusinessServiceHandler {

	private Region region = Region.AP_SOUTHEAST_1;
	
	@Autowired
	private DAOServiceLookup daoService;
	
	protected S3Client getS3Client() {
		S3Client s3Client = S3Client.builder()
                .region(region)
                .build();
		return s3Client;
	}
	
	protected DAOServiceLookup getDaoService() {
		return daoService;
	}
	
	protected ResponseVO getResponseVO() {
	return new ResponseVO(new ResponseInfo(),new ResponseBody());
	}
}
