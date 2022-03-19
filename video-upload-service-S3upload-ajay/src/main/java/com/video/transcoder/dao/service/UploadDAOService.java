package com.video.transcoder.dao.service;

import com.video.transcoder.entity.HlsEntity;
import com.video.transcoder.vo.ContentUploadReqVO;

public interface UploadDAOService {

	public Boolean saveMetaData(ContentUploadReqVO metaData);

}
