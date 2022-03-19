package com.video.transcoder.servie;

import java.util.Optional;

import com.video.transcoder.framework.vo.ResponseVO;
import com.video.transcoder.vo.VideoTransCoderRespVO;
import org.springframework.web.multipart.MultipartFile;

import com.video.transcoder.vo.UploadFileInfoVO;

public interface IStorageService {

	public boolean isVideoUploaded(MultipartFile videoFile) ;
	public Optional<UploadFileInfoVO> writeFileToClassPath(Optional<MultipartFile> videoFile);
	public ResponseVO saveVideoMetaData(String metaDataString, String fileName);
}
