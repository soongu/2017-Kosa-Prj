package com.coderby.myapp.upload.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.upload.model.UploadFileVO;

@Repository
public class UploadFileRepository implements IUploadFileRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int getMaxFileId() {
		String sql = "SELECT NVL(MAX(FILE_ID),0) FROM UPLOAD_FILE";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public void uploadFile(UploadFileVO file) {
		String sql = "INSERT INTO UPLOAD_FILE "
					+ "	(FILE_ID, DIRECTORY_NAME, FILE_NAME, FILE_SIZE, FILE_CONTENT_TYPE, "
					+ "	FILE_UPLOAD_DATE, FILE_DATA) "
					+ "	VALUES (?, ?, ?, ?, ?, SYSDATE, ? )";
		jdbcTemplate.update(sql,
				file.getFileId(),
				file.getDirectoryName(), 
				file.getFileName(), 
				file.getFileSize(), 
				file.getFileContentType(),
				file.getFileData());
	}

	@Override
	public List<UploadFileVO> getFileList(String directoryName) {
	String sql = "SELECT " 
				+"	FILE_ID				AS fileId, " 
				+"	DIRECTORY_NAME		AS directoryName, " 
				+"	FILE_NAME			AS fileName, "
				+"	FILE_SIZE			AS fileSize, "
				+"	FILE_CONTENT_TYPE	AS fileContentType, "
				+"	FILE_UPLOAD_DATE	AS fileUploadDate "
				+"FROM UPLOAD_FILE " 
				+"WHERE DIRECTORY_NAME=? "
				+"ORDER BY FILE_UPLOAD_DATE DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int count) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("fileId"));
				file.setDirectoryName(rs.getString("directoryName"));
				file.setFileName(rs.getString("fileName"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setFileContentType(rs.getString("fileContentType"));
				file.setFileUploadDate(rs.getTimestamp("fileUploadDate"));
				return file;
			}
		}, directoryName);
	}

	@Override
	public List<UploadFileVO> getAllFileList() {
	String sql = "SELECT "
				+"	FILE_ID				AS fileId, "
				+"	DIRECTORY_NAME		AS directoryName, " 
				+"	FILE_NAME			AS fileName, "
				+"	FILE_SIZE			AS fileSize, "
				+"	FILE_CONTENT_TYPE	AS fileContentType, "
				+"	FILE_UPLOAD_DATE	AS fileUploadDate " 
				+"FROM UPLOAD_FILE "
				+"ORDER BY FILE_UPLOAD_DATE DESC";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int count) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("fileId"));
				file.setDirectoryName(rs.getString("directoryName"));
				file.setFileName(rs.getString("fileName"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setFileContentType(rs.getString("fileContentType"));
				file.setFileUploadDate(rs.getTimestamp("fileUploadDate"));
				return file;
			}
		});
	}
	
	@Override
	public List<UploadFileVO> getImageList(String directoryName) {
		String sql = "SELECT " 
				+"	FILE_ID				AS fileId, " 
				+"	DIRECTORY_NAME		AS directoryName, " 
				+"	FILE_NAME			AS fileName, "
				+"	FILE_SIZE			AS fileSize, "
				+"	FILE_CONTENT_TYPE	AS fileContentType, "
				+"	FILE_UPLOAD_DATE	AS fileUploadDate, "
				+"	FILE_DATA			AS fileData "
				+"FROM UPLOAD_FILE " 
				+"WHERE DIRECTORY_NAME=? "
				+"ORDER BY FILE_UPLOAD_DATE DESC ";
		return jdbcTemplate.query(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int count) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("fileId"));
				file.setDirectoryName(rs.getString("directoryName"));
				file.setFileName(rs.getString("fileName"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setFileContentType(rs.getString("fileContentType"));
				file.setFileData(rs.getBytes("fileData"));
				file.setFileUploadDate(rs.getTimestamp("fileUploadDate"));
				return file;
			}
		}, directoryName);
	}
	
	@Override
	public UploadFileVO getFile(int fileId) {
		String sql = "SELECT " 
					+"	FILE_ID				AS fileId, " 
					+"	DIRECTORY_NAME		AS directoryName, " 
					+"	FILE_NAME			AS fileName, "
					+"	FILE_SIZE			AS fileSize, "
					+"	FILE_CONTENT_TYPE	AS fileContentType, " 
					+"	FILE_DATA 			AS fileData "
					+"FROM UPLOAD_FILE "
					+"WHERE FILE_ID=?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<UploadFileVO>(){
			@Override
			public UploadFileVO mapRow(ResultSet rs, int count) throws SQLException {
				UploadFileVO file = new UploadFileVO();
				file.setFileId(rs.getInt("fileId"));
				file.setDirectoryName(rs.getString("directoryName"));
				file.setFileName(rs.getString("fileName"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setFileContentType(rs.getString("fileContentType"));
				file.setFileData(rs.getBytes("fileData"));
				return file;
			}
		}, fileId);
	}

	@Override
	public String getDirectoryName(int fileId) {
		String sql = "SELECT DIRECTORY_NAME	FROM UPLOAD_FILE "
					+"WHERE FILE_ID=?";
		return jdbcTemplate.queryForObject(sql, String.class, fileId);
	}

	@Override
	public void updateDirectory(HashMap<String, Object> map) {
		String sql = "UPDATE UPLOAD_FILE " 
					+"SET DIRECTORY_NAME=? "
					+"WHERE FILE_ID=?";
		jdbcTemplate.update(sql, map.get("directoryName"), map.get("fileId"));
	}
	
	@Override
	public void deleteFile(int fileId) {
		String sql = "DELETE FROM UPLOAD_FILE WHERE FILE_ID=?";
		jdbcTemplate.update(sql, fileId);
	}
	
	@Override
	public void updateFile(UploadFileVO file) {
		String sql = "UPDATE UPLOAD_FILE "
					+"SET DIRECTORY_NAME=?, "
					+"FILE_NAME=?, "
					+"FILE_SIZE=?, "
					+"FILE_CONTENT_TYPE=?, "
					+"FILE_DATA=?" 
					+"WHERE FILE_ID=?";
		jdbcTemplate.update(sql, file.getDirectoryName(), 
								 file.getFileName(), 
								 file.getFileSize(), 
								 file.getFileContentType(),
								 file.getFileData(),
								 file.getFileId());
	}

}