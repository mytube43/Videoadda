package com.video.transcoder.service.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.video.transcoder.dao.service.UploadDAOService;

/**
 * 
 * @author Amit
 *
 */
@Component
public class DAOServiceLookup {

	@Autowired
	UploadDAOService uploadDaoService;

	public UploadDAOService getUploadDaoService() {
		return uploadDaoService;
	}
	
	
}
