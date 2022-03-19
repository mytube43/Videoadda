package com.video.transcoder.dao.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.video.transcoder.entity.HlsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.video.transcoder.vo.ContentUploadReqVO;

@Repository
public class UploadDAOServiceImpl implements UploadDAOService{

	@Autowired
	JdbcTemplate uploadJdbcTemplate;


	private static final String insert_metadata_query= "insert into mytube_metadata_master(user_id,content_tile,"
			+ "content_format,content_file_location,content_m3u8_location) values(?,?,?,?,?)";
	private static final String M3_U8_INP_STATUS = "InProgress";

	@Override
	public Boolean saveMetaData(ContentUploadReqVO metaData) {
		try {
		
		int insertedRow = uploadJdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pStmt = con.prepareStatement(insert_metadata_query);
				pStmt.setInt(1, 1234);
				pStmt.setString(2, metaData.getContentTitle());
				pStmt.setString(3, metaData.getContentFormat());
				pStmt.setString(4, metaData.getRawFileLoc());
				pStmt.setString(5, M3_U8_INP_STATUS);
				return pStmt;
			}
		});
		if(insertedRow>0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}catch (Exception e) {
		System.out.println("Insert exception:: "+e);
	}
		return Boolean.FALSE;
	}


}
